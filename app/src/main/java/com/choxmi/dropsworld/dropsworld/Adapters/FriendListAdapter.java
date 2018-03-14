package com.choxmi.dropsworld.dropsworld.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.Activities.ChatActivity;
import com.choxmi.dropsworld.dropsworld.Activities.ProfileActivity;
import com.choxmi.dropsworld.dropsworld.Models.Friends;
import com.choxmi.dropsworld.dropsworld.R;

import java.util.ArrayList;

/**
 * Created by Choxmi on 11/23/2017.
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> implements Filterable {
    Context context;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    ArrayList<Friends> friends,filteredFriends;

    public FriendListAdapter(Context context, ArrayList<Friends> friends) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.friends = friends;
        filteredFriends = friends;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View model = layoutInflater.inflate(R.layout.model_friends,parent,false);
        return new FriendListAdapter.ViewHolder(model);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TextView friendName = (TextView)holder.itemView.findViewById(R.id.friend_name);
        friendName.setText(filteredFriends.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat = new Intent(context, ChatActivity.class);
                chat.putExtra("contact",filteredFriends.get(position).getName());
                context.startActivity(chat);

            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredFriends.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence keyword) {
                String filterString = keyword.toString();
                filteredFriends = new ArrayList<>();
                if(!filterString.isEmpty()){
                    for(Friends friend:friends){
                        if(friend.getName().toLowerCase().contains(filterString)){
                            filteredFriends.add(friend);
                        }
                    }
                }else{
                    filteredFriends = friends;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredFriends;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredFriends = (ArrayList<Friends>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
