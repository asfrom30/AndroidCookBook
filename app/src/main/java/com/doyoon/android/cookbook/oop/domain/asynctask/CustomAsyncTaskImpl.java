package com.doyoon.android.cookbook.oop.domain.asynctask;

import android.os.AsyncTask;

/**
 * Created by DOYOON on 6/15/2017.
 */

public class CustomAsyncTaskImpl implements CustomAsyncTask {

    public static CustomAsyncTask instance;

    /* Singleton */
    public static CustomAsyncTask getInstance(){
        if (instance == null) {
            instance = new CustomAsyncTaskImpl();
        }
        return instance;
    }

    private CustomAsyncTaskImpl() {

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
                String reuslt = callback.doInBackground(params);
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
}
