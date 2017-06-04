package com.doyoon.android.cookbook.examine.functional;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by DOYOON on 6/1/2017.
 */

public class PermissionUtilTest {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean hasPermissionAndRequestIfNot(Activity activity, String permission, int requestCode) {
        Context context = activity.getBaseContext();

        if(context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED){
            /* 여기에 Activity의 함수를 넣으려면 어떻게 해야 하나? */
            /* run()을 실행시킬 방법이 없음... */
            /* Interface로 설계해서, 익명함수를 쓰면 된다는데.. */
            /* 함수형 언어는 코드를 전달 할 수 있다. */
            /* 람다에서는 코드를 주고 받을 수 있다. JAVA8부터... */
            // run();
            return true;
        } else {
            String permissions[] = {Manifest.permission.READ_CONTACTS};
            activity.requestPermissions(permissions, requestCode);
            return false;
        }
    }

}
