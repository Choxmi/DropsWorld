package com.choxmi.dropsworld.dropsworld.Transaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.choxmi.dropsworld.dropsworld.Config;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Choxmi on 11/11/2017.
 */

public class AssetUploader {
    static LinkedHashMap parameters = new LinkedHashMap();
    static ProgressDialog progressDialog;

    public static void uploadContent(Context context,Post.POST_TYPE type, String content, Post post,AsyncResponse async){
        AsyncResponse delegate = (AsyncResponse) async;
        switch (type){
            case PHOTO:
                try {
                    String filename = new MediaUploader().execute(content).get();
                    Log.e("File",filename);
                    //Upload Rest of the details
                    String[] chunk = filename.split("[.]");
                    parameters.put("type", "PHOTO");
                    parameters.put("id",chunk[0]);
                    parameters.put("fileType",chunk[1]);
                    parameters.put("caption",post.getCaption());
                    parameters.put("user",post.getUser_id());
                    parameters.put("delegate",delegate);
                    String response = new DBOps(Config.DB_UPDATE_URL, parameters).execute().get();
                    parameters.clear();
                    Log.e("Response",response);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            case SIGNUP:
                try {
                    String filename = new MediaUploader().execute(content).get();
                    filename = (filename.split("[.]"))[0]+".jpg";
                    parameters.put("type","SIGNUP");
                    parameters.put("filename",filename);
                    parameters.put("username",post.getUserName());
                    parameters.put("password",post.getCaption());
                    parameters.put("delegate",delegate);
                    String response = null;
                    response = new DBOps(Config.DB_UPDATE_URL, parameters).execute().get();
                    parameters.clear();
                    Log.e("Response SignUP",response);
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }

            case DOODLE:
                try {
                    String filename = new MediaUploader().execute(content).get();
                    filename = (filename.split("[.]"))[0]+".jpg";
                    Log.e("DoodleRes",filename);
                    parameters.put("type","DOODLE");
                    parameters.put("filename",filename);
                    parameters.put("user",post.getUser_id());
                    parameters.put("delegate",delegate);
                    String response = null;
                    response = new DBOps(Config.DB_UPDATE_URL, parameters).execute().get();
                    parameters.clear();
                    Log.e("DoodleRes",response);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            case ALBUM:
                try {
                    String filename = new MediaUploader().execute(content).get();
                    filename = (filename.split("[.]"))[0]+".jpg";
                    parameters.put("type","ALBUM");
                    parameters.put("sub","ADD");
                    parameters.put("filename",filename);
                    parameters.put("album_id",post.getComponent1());
                    parameters.put("user",post.getUser_id());
                    parameters.put("delegate",delegate);
                    String response = null;
                    response = new DBOps(Config.DB_UPDATE_URL, parameters).execute().get();
                    parameters.clear();
                    Log.e("Album Res",response);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            case VIDEO:
                try {
                    String filename = new MediaUploader().execute(content).get();
                    Log.e("File",filename);
                    //Upload Rest of the details
                    String[] chunk = filename.split("[.]");
                    parameters.put("type", "VIDEO");
                    parameters.put("id",chunk[0]);
                    parameters.put("fileType",chunk[1]);
                    parameters.put("caption",post.getCaption());
                    parameters.put("user",post.getUser_id());
                    parameters.put("delegate",delegate);
                    String response = new DBOps(Config.DB_UPDATE_URL, parameters).execute().get();
                    parameters.clear();
                    Log.e("Response",response);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public static void retrieveData(Context context,AsyncResponse async,Post.RETRIEVE_TYPE type, ArrayList<String> content){

        AsyncResponse delegate = (AsyncResponse) async;
        switch (type){
            case POST:
                try {
                    LinkedHashMap params = new LinkedHashMap();
                    params.put("type","POST");
                    params.put("delegate",delegate);
                    String response = new DBOps(Config.RETRIEVE_URL, params).execute().get();
                    Log.e("Retrieve",response);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case LOGIN:
                try {
                    LinkedHashMap params = new LinkedHashMap();
                    params.put("type","LOGIN");
                    params.put("delegate",delegate);
                    params.put("username",content.get(0));
                    params.put("password",content.get(1));
                    String response = new DBOps(Config.RETRIEVE_URL, params).execute().get();
                    Log.e("Retrieve",response);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public static class MediaUploader extends AsyncTask<String, String, String> {
        public MediaUploader(){}

        @Override
        protected String doInBackground(String... params) {
            int serverResponseCode=0;
            String link = "";

            try {

                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1024 * 1024;
                Log.e("Param",params[0]);
                File sourceFile = new File(params[0]);
                Log.e("Param",sourceFile.toString());

                if (sourceFile.isFile()) {

                    try {
                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(Config.ASSET_UP_URL);

                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setUseCaches(true);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE",
                                "multipart/form-data");
                        conn.setRequestProperty("Content-Type",
                                "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("fileName", params[0]);

                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"content\";filename=\""
                                + params[0] + "\"" + lineEnd);
                        dos.writeBytes(lineEnd);

                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);
                        }
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);
                            String line;
                            StringBuilder builder = new StringBuilder();
                            try {
                                InputStream inputStream = new BufferedInputStream(conn.getInputStream());
                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                while ((line = reader.readLine()) != null) {
                                    builder.append(line).append("\n");
                                }
                                reader.close();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            link = builder.toString();

                        fileInputStream.close();
                        dos.flush();
                        dos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.e("Source","Not a file");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return link;
        }
    }

    public static class DBOps extends AsyncTask<String, String, String> {

        String result;
        URL url;
        Map<String,Object> paramList;
        public DBOps(String url, LinkedHashMap paramList) {
            this.result = "";
            try {
                this.url = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            this.paramList = paramList;
        }

        @Override
        protected String doInBackground(String... params) {
            BufferedReader bufferedReader = null;
            HttpURLConnection dc = null;

            try {
                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : paramList.entrySet()) {
                    if (postData.length() != 0) postData.append('&');
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                dc = (HttpURLConnection) url.openConnection();

                dc.setRequestMethod("POST");
                dc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                dc.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                dc.setDoOutput(true);
                dc.getOutputStream().write(postDataBytes);

                bufferedReader = new BufferedReader(new InputStreamReader(dc.getInputStream(),"UTF-8"));

                String line;
                StringBuilder builder = new StringBuilder();
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        builder.append(line).append("\n");
                    }
                    bufferedReader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

                result = builder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            if(paramList.get("delegate")!= null) {
                ((AsyncResponse) paramList.get("delegate")).processFinish(s);
            }
        }
    }
}
