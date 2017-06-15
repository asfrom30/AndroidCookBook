package com.doyoon.android.cookbook.oop.domain.asynctask;

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
    public void execute(Callback callback) {

    }

    @Override
    public String getFetchJsonString(String url) {
        return null;
    }
}
