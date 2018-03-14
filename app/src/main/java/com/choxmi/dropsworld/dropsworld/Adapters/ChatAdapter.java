package com.choxmi.dropsworld.dropsworld.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.Activities.ChatActivity;
import com.choxmi.dropsworld.dropsworld.Fragments.ChatFragmentOld;
import com.choxmi.dropsworld.dropsworld.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Choxmi on 11/1/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Object>  chatMsg;

    public ChatAdapter(Context context, ArrayList<Object> chatMsg){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.chatMsg = chatMsg;
    }

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View model = layoutInflater.inflate(R.layout.model_chat_bubble,parent,false);
        return new ChatAdapter.ViewHolder(model);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(chatMsg.get(holder.getAdapterPosition()) instanceof ChatActivity.ChatStruct) {
            int userType = ((ChatActivity.ChatStruct) chatMsg.get(holder.getAdapterPosition())).user;
            RelativeLayout myBubble = (RelativeLayout) holder.itemView.findViewById(R.id.chat_item_layout_my);
            RelativeLayout otherBubble = (RelativeLayout) holder.itemView.findViewById(R.id.chat_item_layout_other);

            TextView msgMy = (TextView) holder.itemView.findViewById(R.id.chat_msg_my);
            TextView msgOther = (TextView) holder.itemView.findViewById(R.id.chat_msg_other);
            if (userType == 0) {
                otherBubble.setVisibility(View.VISIBLE);
                msgOther.setText(((ChatActivity.ChatStruct) chatMsg.get(holder.getAdapterPosition())).msg);
            } else {
                myBubble.setVisibility(View.VISIBLE);
                msgMy.setText(((ChatActivity.ChatStruct) chatMsg.get(holder.getAdapterPosition())).msg);
            }
        } else if(chatMsg.get(holder.getAdapterPosition()) instanceof ChatActivity.EmojiContainer) {
            int userType = ((ChatActivity.EmojiContainer) chatMsg.get(holder.getAdapterPosition())).user;
            SimpleDraweeView msgMy = (SimpleDraweeView) holder.itemView.findViewById(R.id.my_emo);
            SimpleDraweeView msgOther = (SimpleDraweeView) holder.itemView.findViewById(R.id.other_emo);

            if (userType == 0) {
                msgMy.setVisibility(View.VISIBLE);
                msgMy.setImageDrawable(context.getResources().getDrawable(((ChatActivity.EmojiContainer) chatMsg.get(holder.getAdapterPosition())).emoji,context.getTheme()));
                Log.e("Emoji","Added");
            }else {
                msgOther.setVisibility(View.VISIBLE);
                msgOther.setImageDrawable(context.getResources().getDrawable(((ChatActivity.EmojiContainer) chatMsg.get(holder.getAdapterPosition())).emoji,context.getTheme()));
                Log.e("Emoji","Added");
            }
        }
    }

    @Override
    public int getItemCount() {
        return chatMsg.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}

