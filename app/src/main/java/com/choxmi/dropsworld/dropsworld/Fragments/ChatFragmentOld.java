package com.choxmi.dropsworld.dropsworld.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.choxmi.dropsworld.dropsworld.Activities.CommunityChatActivity;
import com.choxmi.dropsworld.dropsworld.Adapters.ChatAdapter;
import com.choxmi.dropsworld.dropsworld.R;

import java.util.ArrayList;

/**
 * Created by Choxmi on 10/16/2017.
 */

public class ChatFragmentOld extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button communityBtn;
    private ArrayList<ChatStruct> msgs;
    private int[] platformTypes =  {R.drawable.android,R.drawable.xbox};
    private LayoutInflater inflater;
    private Spinner friends;

    public ChatFragmentOld() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ChatFragmentOld newInstance() {
        ChatFragmentOld fragment = new ChatFragmentOld();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        this.inflater = inflater;
        msgs = new ArrayList<>();

        for (int i=0;i < 10;i++){
            int user;
            if((i%2)==0){
                user = 1;
            }else {
                user = 0;
            }
            ChatStruct cs = new ChatStruct(user,"Message "+user+" : "+i);
            msgs.add(cs);
        }

        //final ChatAdapter chatAdapter = new ChatAdapter(getContext(),msgs);

        communityBtn = (Button)rootView.findViewById(R.id.community_btn);
        communityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CommunityChatActivity.class);
                startActivity(intent);
            }
        });

//        final EditText msgInput =(EditText)rootView.findViewById(R.id.msg_input);
//        ImageButton chatSend = (ImageButton)rootView.findViewById(R.id.chat_send);
//        final RecyclerView chat = (RecyclerView)rootView.findViewById(R.id.chat_list);
//        chatSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChatStruct cs = new ChatStruct(1,msgInput.getText().toString());
//                msgs.add(cs);
//                chatAdapter.notifyItemInserted((msgs.size()-1));
//                chat.smoothScrollToPosition((msgs.size()-1));
//            }
//        });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//
//        chat.setLayoutManager(layoutManager);
//        chat.setAdapter(chatAdapter);

        friends = (Spinner)rootView.findViewById(R.id.friend_spinner);
        friends.setAdapter(new SpinnerAdapter());
        friends.setOnItemSelectedListener(this);

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static class ChatStruct{
        public int user = 0;
        public String msg = "";
        public  ChatStruct(int user,String msg){
            this.user = user;
            this.msg = msg;
        }
    }

    private class SpinnerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return platformTypes.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            ViewHolder itemViewHolder;
            if(convertView == null){
                itemView = inflater.inflate(R.layout.platform_row,parent,false);
                itemViewHolder = new ViewHolder();
                itemViewHolder.item = (ImageView) itemView.findViewById(R.id.platform_img);
                itemView.setTag(itemViewHolder);

                itemViewHolder.item.setImageDrawable(getResources().getDrawable(platformTypes[position]));
            }else{
                itemViewHolder = (ViewHolder)itemView.getTag();
            }

            return itemView;
        }
    }

    private static class ViewHolder{
        ImageView item;
    }
}
