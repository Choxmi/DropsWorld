package com.choxmi.dropsworld.dropsworld.Models;

import android.net.Uri;

/**
 * Created by Choxmi on 11/23/2017.
 */

public class Friends {
    private String name;
    private Uri profileUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getProfileUri() {
        return profileUri;
    }

    public void setProfileUri(Uri profileUri) {
        this.profileUri = profileUri;
    }
}
