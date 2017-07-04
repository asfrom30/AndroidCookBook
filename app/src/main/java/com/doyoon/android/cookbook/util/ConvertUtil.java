package com.doyoon.android.cookbook.util;

import java.util.ArrayList;

/**
 * Created by DOYOON on 5/30/2017.
 */

/*
    Version : 0.0.1
 */
/*
    Version : 0.0.2
        add milliSec2MinSec 2017/06/20

 */
public class ConvertUtil {
    public static final String TAG = ConvertUtil.class.getName();

    public static String arrayList2Json(ArrayList arrayList){
//        LogUtil.log(null, TAG, "ArrayList to Json 중 에러가 발생했습니다");
//        return new Gson().toJson(arrayList);
        return null;
    }

    private String milliSec2MinSec(int milliSec){
        long min = milliSec / 1000 /60;
        long sec = milliSec / 1000 % 60;
        return String.format("%02d", min) + " : " + String.format("%02d", sec);
    }
}
