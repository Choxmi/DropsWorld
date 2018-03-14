package com.choxmi.dropsworld.dropsworld.Transaction;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Choxmi on 11/23/2017.
 */

public class Connector extends AsyncTask<String, String, String> {
    public AsyncResponse delegate = null;

    String result;
    URL url;
    public Connector(String urlString) throws MalformedURLException {
        this.result = "";
        this.url = new URL(urlString);
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader bufferedReader = null;
        URLConnection dc = null;
        try {
            dc = url.openConnection();

            Log.e("url",url.toString());
            dc.setConnectTimeout(15000);
            dc.setReadTimeout(15000);

            bufferedReader = new BufferedReader(new InputStreamReader(dc.getInputStream()));
            result = bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
    }
}
