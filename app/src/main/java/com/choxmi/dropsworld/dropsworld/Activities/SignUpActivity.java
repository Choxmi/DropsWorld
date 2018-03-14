package com.choxmi.dropsworld.dropsworld.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;
import com.choxmi.dropsworld.dropsworld.Transaction.AsyncResponse;
import com.choxmi.dropsworld.dropsworld.Transaction.FilePicker;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Choxmi on 12/1/2017.
 */

public class SignUpActivity extends AppCompatActivity implements AsyncResponse{

    SimpleDraweeView profilePic,cover;
    EditText user,pw;
    Button signUp;
    FilePicker filePicker;
    String commonUri,coverUri,profileUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        filePicker = new FilePicker(this);

        profilePic = (SimpleDraweeView)findViewById(R.id.user_pic_up);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePicker.pickImage();
            }
        });

        user = (EditText)findViewById(R.id.user_up_txt);
        pw = (EditText)findViewById(R.id.user_pw_up_txt);

        signUp = (Button)findViewById(R.id.signup_up_btn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();
                post.setUserName(user.getText().toString());
                post.setCaption(pw.getText().toString());
                AssetUploader.uploadContent(SignUpActivity.this, Post.POST_TYPE.SIGNUP,profileUri,post,SignUpActivity.this);
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        commonUri = filePicker.getContentUri(requestCode, resultCode, data);
        profileUri = commonUri;
        profilePic.setImageURI(profileUri);
    }

    @Override
    public void processFinish(String response) {

    }
}
