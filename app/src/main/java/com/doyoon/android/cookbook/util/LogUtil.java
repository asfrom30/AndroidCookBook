package com.doyoon.android.cookbook.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by DOYOON on 5/30/2017.
 */

/*
    Version : 0.0.1
 */

public class LogUtil {

    public static void log(Activity activity, String TAG, String log){
        if(activity != null){
            Toast.makeText(activity, "예기치 못한 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
        }
        Log.d("Say2ME", TAG + " :: " + log);
    }
}
