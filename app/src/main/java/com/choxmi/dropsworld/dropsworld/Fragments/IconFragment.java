package com.choxmi.dropsworld.dropsworld.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.choxmi.dropsworld.dropsworld.Publisher.AssetPublisher;
import com.choxmi.dropsworld.dropsworld.Publisher.QuotePublisher;
import com.choxmi.dropsworld.dropsworld.Publisher.StoryBoardPublisher;
import com.choxmi.dropsworld.dropsworld.Publisher.GamePublisher;
import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/16/2017.
 */

public class IconFragment extends Fragment {

    Button storyBoardBtn,gameBtn,quoteBtn,imgBtn,vidBtn;

    public IconFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static IconFragment newInstance() {
        IconFragment fragment = new IconFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_icon, container, false);

        storyBoardBtn = (Button)rootView.findViewById(R.id.storyboard_btn);
        storyBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StoryBoardPublisher.class);
                startActivity(intent);
            }
        });

        gameBtn = (Button)rootView.findViewById(R.id.game_btn);
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GamePublisher.class);
                startActivity(intent);
            }
        });

        quoteBtn = (Button)rootView.findViewById(R.id.quote_btn);
        quoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuotePublisher.class);
                startActivity(intent);
            }
        });
        imgBtn = (Button)rootView.findViewById(R.id.image_btn);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AssetPublisher.class);
                startActivity(intent);
            }
        });
        vidBtn = (Button)rootView.findViewById(R.id.video_btn);
        vidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AssetPublisher.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
