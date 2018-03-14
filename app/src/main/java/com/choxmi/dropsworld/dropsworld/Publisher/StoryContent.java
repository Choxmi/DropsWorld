package com.choxmi.dropsworld.dropsworld.Publisher;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.choxmi.dropsworld.dropsworld.Adapters.GridItemAdapter;
import com.choxmi.dropsworld.dropsworld.Adapters.StoryBoardContentAdapter;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;
import com.nileshp.multiphotopicker.photopicker.activity.PickImageActivity;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class StoryContent extends AppCompatActivity {

    Button select;
    LinearLayout layout;
    GridView gridView;
    ArrayList<String> selected;
    GridItemAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_storyboard);

        gridView = (GridView)findViewById(R.id.selected_grid);

        selected = new ArrayList<>();
        adapter = new GridItemAdapter(this,selected);
        gridView.setAdapter(adapter);

        layout = (LinearLayout)findViewById(R.id.storyboard_content_layout);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.story_content_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(StoryContent.this));
        recyclerView.setAdapter(new StoryBoardContentAdapter(StoryContent.this));

        select = (Button)findViewById(R.id.select_multi);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(StoryContent.this, PickImageActivity.class);
                mIntent.putExtra(PickImageActivity.KEY_LIMIT_MAX_IMAGE, 60);
                mIntent.putExtra(PickImageActivity.KEY_LIMIT_MIN_IMAGE, 1);
                startActivityForResult(mIntent, PickImageActivity.PICKER_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (resultCode == -1 && requestCode == PickImageActivity.PICKER_REQUEST_CODE) {
            ArrayList<String> pathList = data.getExtras().getStringArrayList(PickImageActivity.KEY_DATA_RESULT);
            if (pathList != null && !pathList.isEmpty()) {
                StringBuilder sb=new StringBuilder("");
                ProgressBar bar = new ProgressBar(StoryContent.this,null,R.attr.progressBarStyle);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                layout.addView(bar,params);

                bar.setVisibility(View.VISIBLE);

                for(int i=0;i<pathList.size();i++) {
                    selected.add(pathList.get(i));
                    sb.append("Photo"+(i+1)+":"+pathList.get(i));
                    sb.append("\n");
                    Post post = new Post();
                    SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String userID = saved_values.getString("owner_id","");
                    post.setUser_id(Integer.valueOf(userID));
                    post.setComponent1("12365");
                    //AssetUploader.uploadContent(StoryContent.this, Post.POST_TYPE.ALBUM,pathList.get(i),post);
                }

                adapter.notifyDataSetChanged();
                Log.e("Output",sb.toString());
                bar.setVisibility(View.INVISIBLE);
            }
        }
    }
}
