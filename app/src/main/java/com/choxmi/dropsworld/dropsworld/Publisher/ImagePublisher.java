package com.choxmi.dropsworld.dropsworld.Publisher;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.choxmi.dropsworld.dropsworld.Activities.LoginActivity;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.Models.User;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;
import com.choxmi.dropsworld.dropsworld.Transaction.AsyncResponse;
import com.choxmi.dropsworld.dropsworld.Transaction.FilePicker;
import com.choxmi.dropsworld.dropsworld.Transaction.ProgressNotification;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class ImagePublisher extends AppCompatActivity implements AsyncResponse {
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_PICK_PHOTO = 2;
    ImageButton captureBtn,pickerBtn;
    SimpleDraweeView selectedImg;
    FilePicker filePicker;
    String uriString;
    EditText captionInput;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publisher_images);
        filePicker = new FilePicker(ImagePublisher.this);
        ActivityCompat.requestPermissions(ImagePublisher.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                100);

        selectedImg = (SimpleDraweeView)findViewById(R.id.selected_asset_img);

        captureBtn = (ImageButton)findViewById(R.id.img_capture);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePicker.captureImage();
            }
        });

        pickerBtn = (ImageButton)findViewById(R.id.img_picker);
        pickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePicker.pickImage();
            }
        });

        captionInput = (EditText)findViewById(R.id.caption_input);

        Button publisherBtn = (Button)findViewById(R.id.imgPublisher);
        publisherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Post post = new Post();
                post.setCaption(captionInput.getText().toString());
                SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String userID = saved_values.getString("owner_id","");
                int userId = 0;
                try {
                    userId = Integer.valueOf(userID);
                }catch (NumberFormatException e){
                    Log.e("Numberformat Occured",e.getLocalizedMessage());
                }
                post.setUser_id(userId);
                ProgressNotification.startLoading(ImagePublisher.this,"Posting...");
                ImgPublisher publisher = new ImgPublisher(post);
                Thread t1 = new Thread(publisher);
                t1.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        uriString = filePicker.getContentUri(requestCode, resultCode, data);
        selectedImg.setImageURI(filePicker.getDisplayUri());
    }

    @Override
    public void processFinish(String response) {
        onBackPressed();
        MDToast.makeText(ImagePublisher.this,response, Toast.LENGTH_LONG,MDToast.TYPE_SUCCESS).show();
        ProgressNotification.stopLoading();
    }

    class ImgPublisher implements Runnable{

        Post post;
        public ImgPublisher(Post post){
            this.post = post;
        }

        @Override
        public void run() {
            ProgressNotification pn = new ProgressNotification();
            pn.startProgress(ImagePublisher.this);
            AssetUploader.uploadContent(ImagePublisher.this, Post.POST_TYPE.PHOTO,uriString,post,ImagePublisher.this);
            pn.stopProgress();
            ProgressNotification.stopLoading();
        }
    }
}
