package com.doyoon.android.cookbook.oop.domain.asynctask;

import android.os.AsyncTask;

/**
 * Created by DOYOON on 6/15/2017.
 */

public class AsyncJsonImpl implements AsyncJson{

    public static AsyncJson instance;

    /* Singleton */
    public static AsyncJson getInstance(){
        if (instance == null) {
            instance = new AsyncJsonImpl();
        }
        return instance;
    }

    private AsyncJsonImpl() {

    }

    @Override
    public void execute(final Callback callback, String stringUrl) {
        new AsyncTask<String, Void, String>() {
            /* Main Thread */
            @Override
            protected void onPreExecute() {

            }

            /* Sub Thread */
            @Override
            protected String doInBackground(String... params) {
                String stringUrl = params[0];
                String reuslt = callback.doInBackground(stringUrl);
                return reuslt;
            }

            /* Main Thread */
            @Override
            protected void onPostExecute(String result) {
                callback.postExecute(result);
            }

            @Override
            protected void onProgressUpdate(Void... values) {

            }


        }.execute(stringUrl);
    }

    @Override
    public String getFetchJsonString(String url) {
        return null;
    }
}
