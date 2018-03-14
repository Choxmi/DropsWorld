package com.choxmi.dropsworld.dropsworld.Transaction;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.Activities.LoginActivity;
import com.choxmi.dropsworld.dropsworld.R;
import com.race604.drawable.wave.WaveDrawable;

/**
 * Created by Choxmi on 12/30/2017.
 */

public class ProgressNotification {
    NotificationManager mNotifyManager;
    NotificationCompat.Builder mBuilder;
    static Dialog dialog;
    static Typeface segoe;

    public void startProgress(Context context){
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("Posting")
                .setContentText("In progress...")
                .setSmallIcon(R.drawable.chatting_selected_r);
        mBuilder.setProgress(0, 0, true);
        mNotifyManager.notify(1, mBuilder.build());
    }

    public void stopProgress(){
        mBuilder.setContentTitle("Posting")
                .setContentText("Completed");
        mBuilder.setProgress(0, 0, false);
        mNotifyManager.notify(1, mBuilder.build());
    }

    public static void startLoading(Context context,String message){
        segoe = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuil.ttf");
        dialog=new Dialog(context,android.R.style.Theme_NoTitleBar_Fullscreen);
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.loading_dialog, null);
        dialog.setContentView(view);
        ImageView loadImg = (ImageView)view.findViewById(R.id.loading_img);
        WaveDrawable mWaveDrawable = new WaveDrawable(context.getDrawable(R.drawable.dropworld_icon));
        mWaveDrawable.setWaveAmplitude(50);
        mWaveDrawable.setWaveLength(500);
        mWaveDrawable.setWaveSpeed(5);
        mWaveDrawable.setIndeterminate(true);
        loadImg.setImageDrawable(mWaveDrawable);

        TextView progressMsg = (TextView)view.findViewById(R.id.loading_txt);
        progressMsg.setTypeface(segoe);
        progressMsg.setText(message);

        dialog.show();
    }
     public static void stopLoading(){
        if(dialog!=null){
            dialog.dismiss();
        }
     }
}
