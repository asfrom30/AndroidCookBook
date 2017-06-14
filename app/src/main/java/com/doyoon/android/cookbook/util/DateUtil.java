package com.doyoon.android.cookbook.util;

import java.text.SimpleDateFormat;

/**
 * Created by pc on 5/29/2017.
 */

/*
    Version : 0.0.1
 */

public class DateUtil {
    public static String convertLongToString(long date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        return sdf.format(date);
    }
}
