package com.choxmi.dropsworld.dropsworld.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.Adapters.UserUploadsAdapter;
import com.choxmi.dropsworld.dropsworld.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Choxmi on 2/27/2018.
 */

public class ProfileFragment extends android.support.v4.app.Fragment{
    RecyclerView my_post_list;
    SimpleDraweeView profilePic;
    public ProfileFragment(){
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actiivity_profile, container, false);
        my_post_list = (RecyclerView)rootView.findViewById(R.id.my_post_view);
        my_post_list.setLayoutManager(new LinearLayoutManager(getContext()));
        my_post_list.setAdapter(new UserUploadsAdapter(getContext()));

        profilePic = (SimpleDraweeView)rootView.findViewById(R.id.profile_page_pic);
        Uri imageUri = Uri.parse("http://www.freepngimg.com/thumb/free/4-2-free-png-thumb.png");
        profilePic.setImageURI(imageUri);

        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = saved_values.getString("owner_id","");

        TextView userName = (TextView)rootView.findViewById(R.id.profile_username);
        userName.setText(username);
        return rootView;
    }
}
