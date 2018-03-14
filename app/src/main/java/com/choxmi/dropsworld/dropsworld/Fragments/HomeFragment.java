package com.choxmi.dropsworld.dropsworld.Fragments;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.choxmi.dropsworld.dropsworld.Adapters.PostItemAdapter;
import com.choxmi.dropsworld.dropsworld.Config;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.Publisher.ImagePublisher;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;
import com.choxmi.dropsworld.dropsworld.Transaction.AsyncResponse;
import com.choxmi.dropsworld.dropsworld.Transaction.Connector;
import com.choxmi.dropsworld.dropsworld.Transaction.JSONConverter;
import com.choxmi.dropsworld.dropsworld.Transaction.ProgressNotification;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Choxmi on 10/16/2017.
 */

public class HomeFragment extends Fragment implements AsyncResponse{

    RecyclerView recyclerView;
    public static PostItemAdapter postItemAdapter;
    Typeface segoe;
    public static ArrayList<Post> posts;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeRefreshLayout.OnRefreshListener refreshListener;

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

        swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.home_refresh);
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PostRetriever retriever = new PostRetriever();
                Thread t1 = new Thread(retriever);
                t1.start();
            }
        };
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        PostRetriever retriever = new PostRetriever();
        Thread t1 = new Thread(retriever);
        t1.start();

        return rootView;
    }

    @Override
    public void processFinish(String response) {
        swipeRefreshLayout.setRefreshing(false);
        Log.e("ProcessFinish",response);
        posts = JSONConverter.getPostList(response);
        if(posts.size()<1){
            MDToast.makeText(getContext(),"No posts found", Toast.LENGTH_LONG,MDToast.TYPE_ERROR).show();
        }
        postItemAdapter = new PostItemAdapter(this.getContext(),this.getActivity(), posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(postItemAdapter);
    }

    class PostRetriever implements Runnable{

        @Override
        public void run() {
            swipeRefreshLayout.setRefreshing(true);
            AssetUploader.retrieveData(getActivity(),HomeFragment.this, Post.RETRIEVE_TYPE.POST,null);
        }
    }
}
