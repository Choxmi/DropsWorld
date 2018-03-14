package com.choxmi.dropsworld.dropsworld.Transaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.choxmi.dropsworld.dropsworld.Models.Post;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Choxmi on 12/1/2017.
 */

public class FilePicker {
    final int CAMERA_CAPTURE_REQUEST = 1;
    final int CAMERA_PICK_REQUEST = 2;
    final int REQUEST_VIDEO_CAPTURE = 3;
    final int REQUEST_PICK_VIDEO = 4;
    File finalFile = null;
    String uri;
    Context context;
    Activity activity;
    Uri displayUri;

    public FilePicker(Activity activity){
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public String getContentUri(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Bitmap compresses = getResizedBitmap(photo,1024);
            Uri tempUri = getImageUri(context, compresses);
            displayUri = tempUri;
            finalFile = new File(getRealPathFromURI(tempUri));
            uri = getRealPathFromURI(tempUri);
        }
        if (requestCode == CAMERA_PICK_REQUEST && resultCode == RESULT_OK) {
            Uri imgUri = (Uri)data.getData();
            try {
                Bitmap original = MediaStore.Images.Media.getBitmap(context.getContentResolver(),imgUri);
                imgUri = getImageUri(context, original);
                finalFile = new File(getRealPathFromURI(imgUri));
                this.uri = getRealPathFromURI(imgUri);
                displayUri = Uri.fromFile(finalFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            uri = getRealPathFromURI(videoUri);
        }
        if(requestCode == REQUEST_PICK_VIDEO && resultCode == RESULT_OK){
            Uri videoUri = data.getData();
            uri = getRealPathFromURI(videoUri);
        }

        return uri;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.WEBP, 1, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
//        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = 0;
//        idx = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
        idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public String getRealVideoPath(Context context,Uri uri){
        String[] filePathColumn = {MediaStore.Video.Media.DATA};
        String realPath="";
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
        if(cursor != null){
            int index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(index);
        } else {
            Log.e("Error","No column");
        }
        cursor.close();
        return realPath;
    }

    public Bitmap getResizedBitmap(Bitmap image, int widthFixed) {
        int height = (widthFixed/image.getWidth())*image.getHeight();

        return Bitmap.createScaledBitmap(image, widthFixed, height, true);
    }

    public void captureImage(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, CAMERA_CAPTURE_REQUEST);
    }

    public void captureVideo(){
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(activity.getApplicationContext().getPackageManager()) != null) {
            activity.startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    public void pickImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent,CAMERA_PICK_REQUEST);
    }

    public void pickVideo(){
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent,REQUEST_PICK_VIDEO);
    }

    public Uri getDisplayUri(){
        return displayUri;
    }
}
