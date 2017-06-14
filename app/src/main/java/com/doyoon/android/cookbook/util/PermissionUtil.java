package com.doyoon.android.cookbook.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by DOYOON on 6/1/2017.
 */

/*
    Version : 0.0.1
 */
/*
    Version : 0.0.2
        @param in PermissionUtil Class Type changed(Activity Class to Context Class)
        depend on parent class
 */

public class PermissionUtil {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean hasPermissionsAndRequestIfNot(Activity activity, String[] permissions, int requestCode) {
        Context context = activity.getBaseContext();

        for (String permission : permissions) {
            if(context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                activity.requestPermissions(permissions, requestCode);
                return false;
            }
        }
        return true;
    }
}
