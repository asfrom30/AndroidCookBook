package com.doyoon.android.cookbook.util.uri;

import android.net.Uri;

/**
 * Created by DOYOON on 6/26/2017.
 */

public class UriConverter {

    public static String uri2String(Uri uri){
        String strUri = uri.toString();
        return strUri;
    }

    public static Uri String2Uri(String strUri){
        Uri uri = Uri.parse(strUri);
        return uri;
    }
}
