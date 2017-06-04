package com.doyoon.android.cookbook.examine.functional;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.cookbook.R;

public class Functional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            boolean hasPermission = PermissionUtilTest.hasPermissionAndRequestIfNot(this, Manifest.permission.READ_CONTACTS, 100);
            /* 아래 코드를 다른 함수로 param에 실어서 전달 할 수만 있다면.. 삭제될 수 있다.
            * 객체의 함수를 전달하지 못해서, boolean 값으로 따로 받아와서 다시 실행함... */
            if(hasPermission){
                /* run(); */
            }
        }
    }
}
