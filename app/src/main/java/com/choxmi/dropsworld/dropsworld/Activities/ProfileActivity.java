package com.choxmi.dropsworld.dropsworld.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.Adapters.UserUploadsAdapter;
import com.choxmi.dropsworld.dropsworld.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Choxmi on 10/31/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    RecyclerView my_post_list;
    SimpleDraweeView profilePic;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_profile);

        my_post_list = (RecyclerView)findViewById(R.id.my_post_view);
        my_post_list.setLayoutManager(new LinearLayoutManager(this));
        my_post_list.setAdapter(new UserUploadsAdapter(this));

        profilePic = (SimpleDraweeView)findViewById(R.id.profile_page_pic);
        Uri imageUri = Uri.parse("http://www.freepngimg.com/thumb/free/4-2-free-png-thumb.png");
        profilePic.setImageURI(imageUri);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        TextView userName = (TextView)findViewById(R.id.profile_username);
        userName.setText(username);
    }
}
