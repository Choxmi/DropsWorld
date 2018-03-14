package com.choxmi.dropsworld.dropsworld.Transaction;

import android.net.Uri;
import android.util.Log;

import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Choxmi on 11/28/2017.
 */

public class JSONConverter {

    public static ArrayList<Post> getPostList(String data){
        ArrayList<Post> posts = new ArrayList<>();

        Log.e("Content", data);

        try {
            JSONArray ja = new JSONArray(data);
            for(int i=0;i<ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);
                Post post = new Post();
                post.setType(jo.getString("type"));
                if(post.getType().equals("PHOTO")) {
                    post.setCaption(jo.getString("caption"));
                    post.setUrl1(jo.getString("url1"));
                }if(post.getType().equals("QUOTE")) {
                    post.setCaption(jo.getString("caption"));
                    post.setComponent1(jo.getString("component1"));
                    post.setComponent2(jo.getString("component2"));
                    post.setComponent3(jo.getString("component3"));
                }if(post.getType().equals("DOODLE")) {;
                    post.setUrl1(jo.getString("url1"));
                }if(post.getType().equals("VIDEO")) {
                    post.setCaption(jo.getString("caption"));
                    post.setUrl1(jo.getString("url1"));
                }
                post.setUserName(jo.getString("user_name"));
                post.setUserUrl(jo.getString("user_pic"));
                post.setDate(jo.getString("date"));
                posts.add(post);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static User getUser(String data){
        User user = new User();
        try {
            JSONArray ja = new JSONArray(data);
            if(ja.length()>1){
                return null;
            }else{
                JSONObject jo = ja.getJSONObject(0);
                user.setUserID(jo.getString("user_id"));
                user.setUserName(jo.getString("user_name"));
                user.setUserPic(jo.getString("user_pic"));
                user.setUserCover(jo.getString("user_cover"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
