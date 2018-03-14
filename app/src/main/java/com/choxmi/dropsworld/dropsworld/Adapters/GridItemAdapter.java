package com.choxmi.dropsworld.dropsworld.Adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.FilePicker;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Choxmi on 1/26/2018.
 */

public class GridItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> imgList;

    public GridItemAdapter(Context c,ArrayList<String> imgList){
        context = c;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.grid_model, null, false);
        //view.setLayoutParams(new GridView.LayoutParams(100,100));
        SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.grid_item_img);

        img.setImageURI(Uri.fromFile(new File(imgList.get(position))));

//        SimpleDraweeView img = new SimpleDraweeView(context);
//        img.setLayoutParams(new GridView.LayoutParams(85,85));
//        img.setImageURI(imgList.get(position));
//        img.setBackgroundColor(Color.BLACK);

        return view;
    }
}
