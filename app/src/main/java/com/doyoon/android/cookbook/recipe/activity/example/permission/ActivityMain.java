package com.doyoon.android.cookbook.recipe.activity.example.permission;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.doyoon.android.cookbook.R;
import com.doyoon.android.cookbook.recipe.activity.PermissionActivity;

public class ActivityMain extends PermissionActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oop_recipe_permission_main_layout);
    }

    @Override
    public String[] throwNeedPermissions() {
        String permissions[] = {Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS};
        return permissions;
    }

    @Override
    public void run() {
        Toast.makeText(this, "승인되었습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void cancel() {
        Toast.makeText(this, "거절되었습니다..", Toast.LENGTH_LONG).show();
    }
}
