package com.choxmi.dropsworld.dropsworld.Publisher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class GamePublisher extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private int[] platformTypes =  {R.drawable.android,R.drawable.xbox};

    private static class ViewHolder{
        ImageView item;
    }

    private class SpinnerAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return platformTypes.length;
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
            View itemView = convertView;
            ViewHolder itemViewHolder;
            Log.e("Setting","Done");
            if(convertView == null){
                itemView = getLayoutInflater().inflate(R.layout.platform_row,parent,false);
                itemViewHolder = new ViewHolder();
                itemViewHolder.item = (ImageView) itemView.findViewById(R.id.platform_img);
                itemView.setTag(itemViewHolder);

                itemViewHolder.item.setImageDrawable(getResources().getDrawable(platformTypes[position]));
            }else{
                itemViewHolder = (ViewHolder)itemView.getTag();
            }

            return itemView;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publisher_games);

        Spinner platforms = (Spinner)findViewById(R.id.platform_spinner);
        platforms.setAdapter(new SpinnerAdapter());
        platforms.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
