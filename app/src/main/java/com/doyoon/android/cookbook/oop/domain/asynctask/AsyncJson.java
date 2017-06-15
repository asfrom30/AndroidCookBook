package com.doyoon.android.cookbook.oop.domain.asynctask;

/**
 * Created by DOYOON on 6/15/2017.
 */

public interface AsyncJson {

    public void execute(Callback callback, String stringUrl);
    public String getFetchJsonString(String url);

    public interface Callback {
        public void preExecute();
        public String doInBackground(String stringUrl);
        public void postExecute(String result);
    }
}
