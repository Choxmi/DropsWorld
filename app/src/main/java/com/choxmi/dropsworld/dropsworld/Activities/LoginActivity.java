package com.choxmi.dropsworld.dropsworld.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.choxmi.dropsworld.dropsworld.Fragments.HomeFragment;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.Models.User;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;
import com.choxmi.dropsworld.dropsworld.Transaction.AsyncResponse;
import com.choxmi.dropsworld.dropsworld.Transaction.JSONConverter;
import com.choxmi.dropsworld.dropsworld.Transaction.ProgressNotification;
import com.google.gson.Gson;
import com.race604.drawable.wave.WaveDrawable;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;

/**
 * Created by Choxmi on 12/1/2017.
 */

public class LoginActivity extends AppCompatActivity implements AsyncResponse {

    Button signUpBtn,loginBtn;
    EditText username,password;
    ArrayList<String> content;
    ImageView logoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logoView = (ImageView)findViewById(R.id.logo_view);

//        sharedPref = getPreferences(Context.MODE_PRIVATE);
//
//        String json = sharedPref.getString("owner_id", "");
//        if(!"".equals(json)){
//            Intent login = new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(login);
//        }

        loginBtn = (Button)findViewById(R.id.login_btn);
        signUpBtn = (Button)findViewById(R.id.to_signup_btn);

        username = (EditText)findViewById(R.id.username_txt);
        password = (EditText)findViewById(R.id.password_txt);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signup);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressNotification.startLoading(LoginActivity.this,"Signing in...");
                content= new ArrayList<>();
                content.add(username.getText().toString());
                content.add(password.getText().toString());
                LoginHandler handler = new LoginHandler();
                Thread tLogin = new Thread(handler);
                tLogin.start();
            }
        });
    }

    @Override
    public void processFinish(String response) {
        Log.e("RES",response);
        if(!response.trim().equals("404")) {
            User owner = JSONConverter.getUser(response);
            SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = saved_values.edit();
            editor.putString("owner_id", owner.getUserID());
            editor.putString("owner_name", owner.getUserName());
            editor.putString("owner_pic", owner.getUserPic());
            editor.putString("owner_cover", owner.getUserCover());
            editor.commit();
            Intent login = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(login);
        }else {
            MDToast.makeText(LoginActivity.this,"Wrong credentials", Toast.LENGTH_LONG,MDToast.TYPE_WARNING).show();
            ProgressNotification.stopLoading();
        }
    }


    class LoginHandler implements Runnable{

        @Override
        public void run() {
            Log.e("THREAD","Started");
            AssetUploader.retrieveData(LoginActivity.this,LoginActivity.this, Post.RETRIEVE_TYPE.LOGIN,content);
        }
    }
}
