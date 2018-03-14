package com.choxmi.dropsworld.dropsworld.Publisher;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;
import com.choxmi.dropsworld.dropsworld.Transaction.AsyncResponse;
import com.choxmi.dropsworld.dropsworld.Transaction.FilePicker;
import com.choxmi.dropsworld.dropsworld.Transaction.ProgressNotification;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.io.File;

/**
 * Created by Choxmi on 11/6/2017.
 */

public class VideoPublisher extends AppCompatActivity implements AsyncResponse {

    ImageButton captureVid,uploadVid;
    Button publisher;
    VideoView vid;
    FilePicker filePicker;
    Uri videoUri = null;
    String selectedUri;
    File vidFile;

    static final int REQUEST_PICK_VIDEO = 4;
    static final int REQUEST_VIDEO_CAPTURE = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publisher_videos);
        filePicker = new FilePicker(VideoPublisher.this);
        ActivityCompat.requestPermissions(VideoPublisher.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                100);
        ActivityCompat.requestPermissions(VideoPublisher.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                100);

        vid = (VideoView)findViewById(R.id.selected_video);

        captureVid = (ImageButton)findViewById(R.id.vid_capture);
        captureVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureVideo();
            }
        });

        uploadVid = (ImageButton)findViewById(R.id.vid_picker);
        uploadVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickVideo();
            }
        });

        publisher = (Button)findViewById(R.id.btnPublishVid);
        publisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoUri!=null) {
                    final Post post = new Post();
                    post.setCaption("Sample");
                    SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String userID = saved_values.getString("owner_id","");
                    int userId = 0;
                    try {
                        userId = Integer.valueOf(userID);
                    }catch (NumberFormatException e){
                        Log.e("Numberformat Occured",e.getLocalizedMessage());
                    }
                    post.setUser_id(userId);
                    ProgressNotification.startLoading(VideoPublisher.this,"Posting...");
                    VidPublisher publisher = new VidPublisher(post);
                    Thread t1 = new Thread(publisher);
                    t1.start();
                }else{
                    MDToast.makeText(VideoPublisher.this,"Choose/Capture first", Toast.LENGTH_LONG,MDToast.TYPE_ERROR).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = intent.getData();

            vid.setVideoURI(videoUri);
            vid.start();
        }else if(requestCode == REQUEST_PICK_VIDEO && resultCode == RESULT_OK){
//            videoUri = intent.getData();
//            Log.e("PATH",getPath(videoUri));
//            vid.setVideoURI(videoUri);
//
            Uri selectedVidUri = intent.getData();
            videoUri = selectedVidUri;
            selectedUri = filePicker.getRealVideoPath(VideoPublisher.this,selectedVidUri);
            vidFile = new File(selectedUri);
            Log.e("selectedUri 1",selectedUri);
            selectedUri = filePicker.getContentUri(requestCode, resultCode, intent);
            Log.e("selectedUri 1",selectedUri);
            vid.setVideoURI(Uri.fromFile(vidFile));
        }
    }

    private void captureVideo() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    private void pickVideo(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_PICK_VIDEO);
    }

    public String getPath(Uri uri) {
        Cursor cursor = null;
        try {
            String[] projection = {MediaStore.Images.Media.DATA};
            cursor = getContentResolver().query(uri, projection, null, null, null);

            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void processFinish(String response) {
        MDToast.makeText(VideoPublisher.this,response, Toast.LENGTH_LONG,MDToast.TYPE_ERROR).show();
    }

//    public String getPath(Uri uri) {
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        String document_id = cursor.getString(0);
//        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
//        cursor.close();
//
//        cursor = getContentResolver().query(
//                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
//                null, MediaStore.Video.Media._ID + " = ? ", new String[]{document_id}, null);
//        cursor.moveToFirst();
//        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
//        cursor.close();
//
//        return path;
//    }

    class VidPublisher implements Runnable{

        Post post;
        public VidPublisher(Post post){
            this.post = post;
        }

        @Override
        public void run() {
            ProgressNotification pn = new ProgressNotification();
            pn.startProgress(VideoPublisher.this);
            AssetUploader.uploadContent(VideoPublisher.this, Post.POST_TYPE.VIDEO,selectedUri,post,VideoPublisher.this);
            pn.stopProgress();
            ProgressNotification.stopLoading();
        }
    }
}
