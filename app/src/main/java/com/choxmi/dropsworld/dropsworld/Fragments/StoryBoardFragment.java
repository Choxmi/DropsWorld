package com.choxmi.dropsworld.dropsworld.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choxmi.dropsworld.dropsworld.Adapters.StoryBoardContentAdapter;
import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class StoryBoardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View storyFragment = inflater.inflate(R.layout.fragment_storyboard,container,false);

        final RecyclerView recyclerView = (RecyclerView) storyFragment.findViewById(R.id.story_content_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new StoryBoardContentAdapter(getContext()));

        return storyFragment;
    }
}
