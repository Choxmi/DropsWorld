package com.choxmi.dropsworld.dropsworld.Publisher;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.choxmi.dropsworld.dropsworld.Config;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;
import com.choxmi.dropsworld.dropsworld.Transaction.AsyncResponse;
import com.choxmi.dropsworld.dropsworld.Transaction.FilePicker;
import com.facebook.drawee.view.SimpleDraweeView;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Choxmi on 11/5/2017.
 */

public class HeadWordPublisher extends AppCompatActivity implements AsyncResponse {

    ImageButton capVid,capImg,pickVid,pickImg;
    Button publisherBtn;
    SimpleDraweeView sImg;
    VideoView sVid;
    final int CAMERA_CAPTURE_REQUEST = 1;
    final int CAMERA_PICK_REQUEST = 2;
    final int REQUEST_VIDEO_CAPTURE = 3;
    final int REQUEST_PICK_VIDEO = 4;
    String imgUri = null;
    String vidUri = null;
    FilePicker filePicker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publisher_headword);
        filePicker = new FilePicker(HeadWordPublisher.this);
        sImg = (SimpleDraweeView)findViewById(R.id.selected_asset_img);
        sVid =(VideoView)findViewById(R.id.selected_asset_vid);

        capImg = (ImageButton)findViewById(R.id.img_capture_hw);
        capImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePicker.captureImage();
            }
        });
        capVid = (ImageButton)findViewById(R.id.vid_capture_hw);
        capVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePicker.captureVideo();
            }
        });
        pickVid = (ImageButton)findViewById(R.id.img_picker_hw);
        pickVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePicker.pickImage();
            }
        });
        pickImg = (ImageButton)findViewById(R.id.vid_picker_hw);
        pickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePicker.pickVideo();
            }
        });

        publisherBtn = (Button)findViewById(R.id.publisherBtnHw);
        publisherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();
                if(imgUri!=null&&vidUri!=null){
                    AssetUploader.uploadContent(HeadWordPublisher.this,Post.POST_TYPE.PHOTO,imgUri,post,HeadWordPublisher.this);
                    AssetUploader.uploadContent(HeadWordPublisher.this,Post.POST_TYPE.VIDEO,vidUri,post,HeadWordPublisher.this);
                }else {
                    Toast.makeText(HeadWordPublisher.this,"Select Image and Video!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String uri = filePicker.getContentUri(requestCode, resultCode, data);
        if(requestCode==CAMERA_CAPTURE_REQUEST||requestCode==CAMERA_PICK_REQUEST){
            imgUri = uri;
            sImg.setImageURI(imgUri);
        }
        if(requestCode==REQUEST_VIDEO_CAPTURE||requestCode==REQUEST_PICK_VIDEO){
            vidUri = uri;
            sVid.setVideoURI(Uri.parse(uri));
            sVid.start();
        }
    }

    @Override
    public void processFinish(String response) {

    }
}
