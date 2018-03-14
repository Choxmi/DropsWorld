package com.choxmi.dropsworld.dropsworld.Activities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.Adapters.ChatAdapter;
import com.choxmi.dropsworld.dropsworld.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Choxmi on 12/16/2017.
 */

public class ChatActivity extends AppCompatActivity {

    private ArrayList<Object> msgs;

    public static class ChatStruct{
        public int user = 0;
        public String msg = "";
        public  ChatStruct(int user,String msg){
            this.user = user;
            this.msg = msg;
        }
    }

    public static class EmojiContainer {
        public int user = 0;
        public int emoji;
        public int audio;
        public EmojiContainer(int emo,int audio,int user){
            this.user = user;
            this.emoji = emo;
            this.audio = audio;
        }
    }

    SimpleDraweeView profilePic;
    TextView profileName;
    ImageButton speakEmo;
    ImageButton emo1,emo2,emo3,emo4,emo5,emo6;
    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        profileName = (TextView)findViewById(R.id.chat_name);
        profileName.setText(getIntent().getStringExtra("contact"));

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

        final ChatAdapter chatAdapter = new ChatAdapter(getApplicationContext(),msgs);

        final EditText msgInput =(EditText)findViewById(R.id.msgInput);
        Button chatSend = (Button)findViewById(R.id.msgSend);
        final RecyclerView chat = (RecyclerView)findViewById(R.id.chat_recycle);
        chatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatStruct cs = new ChatStruct(1,msgInput.getText().toString());
                msgs.add(cs);
                chatAdapter.notifyItemInserted((msgs.size()-1));
                chat.smoothScrollToPosition((msgs.size()-1));
            }
        });

        speakEmo = (ImageButton)findViewById(R.id.speak_emo_btn);
        speakEmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                LayoutInflater inflater = ChatActivity.this.getLayoutInflater();

                View view = inflater.inflate(R.layout.model_voice_emoji, null);

                builder.setView(view);

                emo1 = (ImageButton)view.findViewById(R.id.emo1);
                emo1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Emo","1");
                        EmojiContainer emo = new EmojiContainer(R.drawable.emo1,R.drawable.emo1,0);
                        msgs.add(emo);
                        dialog.dismiss();
                        chatAdapter.notifyItemInserted((msgs.size()-1));
                        chat.smoothScrollToPosition((msgs.size()-1));
                    }
                });

                emo2 = (ImageButton)view.findViewById(R.id.emo2);
                emo2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Emo","2");
                    }
                });


                emo3 = (ImageButton)view.findViewById(R.id.emo3);
                emo3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Emo","3");
                    }
                });

                emo4 = (ImageButton)view.findViewById(R.id.emo4);
                emo4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Emo","4");
                    }
                });

                emo5 = (ImageButton)view.findViewById(R.id.emo5);
                emo5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Emo","5");
                    }
                });

                emo6 = (ImageButton)view.findViewById(R.id.emo6);
                emo6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Emo","6");
                    }
                });

                dialog = builder.create();
                dialog.show();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        chat.setLayoutManager(layoutManager);
        chat.setAdapter(chatAdapter);
    }
}
