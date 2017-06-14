package com.doyoon.android.cookbook.oop.interfaces.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DOYOON on 6/13/2017.
 */

public class Remote {
    public void getData(final Callback obj) {

        String urlString = obj.getUrlString();

        /* protocol declaration */
        if (!urlString.startsWith("http")) {
            urlString = "http://" + urlString;
        }

        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                obj.getProgress().setProgressStyle(ProgressDialog.STYLE_SPINNER);
                obj.getProgress().setMessage("불러오는중...");
                obj.getProgress().show();
            }

            @Override
            protected String doInBackground(String... params) {
                String urlString = params[0];
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                        StringBuilder result = new StringBuilder();
                        String lineOfData = "";
                        while ((lineOfData = br.readLine()) != null) {
                            result.append(lineOfData);
                        }

                        return result.toString();   /* return 을 이지점에서?? */
                    } else {
                        Log.e("HTTPConnection", "Error Code=" + responseCode);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                obj.postExecute(result);
            }
        }.execute(urlString);
    }

    public interface Callback {
        public Context getContext();
        public ProgressDialog getProgress();
        public String getUrlString();
        public void postExecute(String jsonString);
    }
}
