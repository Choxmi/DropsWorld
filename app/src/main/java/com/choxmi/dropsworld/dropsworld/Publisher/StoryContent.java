package com.choxmi.dropsworld.dropsworld.Publisher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.choxmi.dropsworld.dropsworld.Adapters.StoryBoardContentAdapter;
import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class StoryContent extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_storyboard);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.story_content_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(StoryContent.this));
        recyclerView.setAdapter(new StoryBoardContentAdapter(StoryContent.this));
    }
}
