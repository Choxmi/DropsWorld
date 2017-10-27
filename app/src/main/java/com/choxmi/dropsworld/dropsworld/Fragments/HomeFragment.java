package com.choxmi.dropsworld.dropsworld.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.Adapters.PostItemAdapter;
import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/16/2017.
 */

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    PostItemAdapter postItemAdapter;
    TextView txt;
    Typeface segoe;

    public HomeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        segoe = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoeuil.ttf");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        postItemAdapter = new PostItemAdapter(this.getContext());
        recyclerView.setAdapter(postItemAdapter);

//        txt = (TextView)rootView.findViewById(R.id.textView3);
//        txt.setTypeface(segoe);

        return rootView;
    }
}
