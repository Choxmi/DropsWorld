package com.choxmi.dropsworld.dropsworld;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Choxmi on 10/20/2017.
 */

public class DropsWorldApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
       Fresco.initialize(this);
    }

    public static Context getAppContext(){
        return DropsWorldApplication.context;
    }
}
