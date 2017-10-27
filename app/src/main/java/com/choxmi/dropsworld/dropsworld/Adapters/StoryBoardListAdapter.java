package com.choxmi.dropsworld.dropsworld.Adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.choxmi.dropsworld.dropsworld.Publisher.StoryContent;
import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class StoryBoardListAdapter extends RecyclerView.Adapter<StoryBoardListAdapter.ViewHolder>{

    Context context;
    LayoutInflater layoutInflater;
    FragmentManager fragmentManager;
    ViewGroup viewGroup;

    public StoryBoardListAdapter(Context context,android.app.FragmentManager fragmentManager){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public StoryBoardListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View model = layoutInflater.inflate(R.layout.model_storyboard_list,parent,false);
        viewGroup = (ViewGroup) parent.getParent();
        return new ViewHolder(model);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.storyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                StoryBoardFragment storyBoardFragment = new StoryBoardFragment();
//                transaction.add(viewGroup.getId(),storyBoardFragment);
//                transaction.commit();

                Intent intent = new Intent(context, StoryContent.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout storyItem;
        public ViewHolder(View itemView) {
            super(itemView);
            storyItem = (LinearLayout)itemView.findViewById(R.id.story_list_container);
        }
    }
}
