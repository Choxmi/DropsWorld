package com.choxmi.dropsworld.dropsworld.Publisher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.choxmi.dropsworld.dropsworld.Adapters.StoryBoardListAdapter;
import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/21/2017.
 */

public class StoryBoardPublisher extends AppCompatActivity{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyboard);

        android.app.FragmentManager fragmentManager = getFragmentManager();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.story_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StoryBoardListAdapter(StoryBoardPublisher.this,fragmentManager));
    }
}
