package com.choxmi.dropsworld.dropsworld.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choxmi.dropsworld.dropsworld.Adapters.FriendListAdapter;
import com.choxmi.dropsworld.dropsworld.Models.Friends;
import com.choxmi.dropsworld.dropsworld.R;

import java.util.ArrayList;

/**
 * Created by Choxmi on 11/23/2017.
 */

public class ChatFragment extends Fragment {

    private FriendListAdapter mAdapter;
    private RecyclerView recyclerView;

    public ChatFragment() {
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_select, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.friend_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new FriendListAdapter(getContext(),generateFriendList());
        recyclerView.setAdapter(mAdapter);
        android.support.v7.widget.SearchView friendSearch = (android.support.v7.widget.SearchView)view.findViewById(R.id.friend_search);
        search(friendSearch);
        return view;
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    private ArrayList<Friends> generateFriendList(){
        ArrayList<Friends> friendList = new ArrayList<>();

        for(int i=0;i<10;i++){
            Friends friend = new Friends();
            friend.setName("FRI "+(i+1));
            friend.setProfileUri(null);
            friendList.add(friend);
        }

        return friendList;
    }
}