package com.choxmi.dropsworld.dropsworld.Models;

import android.net.Uri;

/**
 * Created by Choxmi on 11/28/2017.
 */

public class Post {

    private int post_id;
    private int user_id;
    private int likes;
    private String type;
    private String url1;
    private String url2;
    private String url3;
    private String caption;
    private String component1;
    private String component2;
    private String component3;
    private String component4;
    private String date;
    private String userName;
    private String userUrl;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getComponent1() {
        return component1;
    }

    public void setComponent1(String component1) {
        this.component1 = component1;
    }

    public String getComponent2() {
        return component2;
    }

    public void setComponent2(String component2) {
        this.component2 = component2;
    }

    public String getComponent3() {
        return component3;
    }

    public void setComponent3(String component3) {
        this.component3 = component3;
    }

    public String getComponent4() {
        return component4;
    }

    public void setComponent4(String component4) {
        this.component4 = component4;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }


    public enum POST_TYPE{
        PHOTO,
        VIDEO,
        QUOTE,
        HEADWORD,
        DOODLE,
        STORY,
        SIGNUP,
        ALBUM
    }

    public enum RETRIEVE_TYPE{
        POST,
        LOGIN
    }

    public class comment{
        private String user;
        private Uri userPic;
        private String comment;
    }
}
