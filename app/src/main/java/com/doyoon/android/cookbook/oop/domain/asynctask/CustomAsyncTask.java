package com.doyoon.android.cookbook.oop.domain.asynctask;

/**
 * Created by DOYOON on 6/15/2017.
 */

public interface CustomAsyncTask {

    public void execute(Callback callback, String stringUrl);

    public interface Callback {
        public void preExecute();
        public String doInBackground(String... params);
        public void postExecute(String result);
    }
}
