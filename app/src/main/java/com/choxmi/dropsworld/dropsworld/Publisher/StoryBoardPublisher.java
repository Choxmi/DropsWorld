package com.choxmi.dropsworld.dropsworld.Publisher;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.choxmi.dropsworld.dropsworld.Adapters.StoryBoardListAdapter;
import com.choxmi.dropsworld.dropsworld.Config;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by Choxmi on 10/21/2017.
 */

public class StoryBoardPublisher extends AppCompatActivity{

    Button createAlbum;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyboard);

        android.app.FragmentManager fragmentManager = getFragmentManager();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.story_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StoryBoardListAdapter(StoryBoardPublisher.this,fragmentManager));

        createAlbum = (Button)findViewById(R.id.create_album_btn);
        createAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedHashMap parameters = new LinkedHashMap();
                parameters.put("type","ALBUM");
                parameters.put("sub","CREATE");
                parameters.put("album_name","Test");
                parameters.put("album_id",12365);
                SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String userID = saved_values.getString("owner_id","");
                parameters.put("user",userID);
                try {
                    String response = new AssetUploader.DBOps(Config.DB_UPDATE_URL, parameters).execute().get();
                    Log.e("Response",response);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
