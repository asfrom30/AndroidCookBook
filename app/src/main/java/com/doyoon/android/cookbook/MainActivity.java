package com.doyoon.android.cookbook;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.doyoon.android.cookbook.util.DeviceUtil;
import com.doyoon.android.cookbook.util.PermissionControl;

public class MainActivity extends AppCompatActivity implements PermissionControl.Callback, DeviceUtil.Camera.Callback, View.OnClickListener{


    private ImageView imageView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imageView = (ImageView) findViewById(R.id.main_iv);
        this.btn = (Button) findViewById(R.id.main_btn);
        this.btn.setEnabled(false);
        this.btn.setOnClickListener(this);

        String permissions[] = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionControl.requestAndRunOrNot(this, this, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.run();
    }

    @Override
    public void run() {
        this.btn.setEnabled(true);
    }

    @Override
    public void cancle() {

    }
    /* Camera Action */

    @Override
    public void onClick(View v) {
       DeviceUtil.Camera.takePhoto(this, "doyoon", DeviceUtil.CAMERA_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case DeviceUtil.CAMERA_REQ_CODE :
                if(resultCode == RESULT_OK) DeviceUtil.Camera.onActivityResult(requestCode, resultCode, data, getBaseContext(), this);
                else Log.e("TAG", "Result Code를 체크해주세요.");
                break;
            default:
                break;
        }

    }

    @Override
    public void postProcAfterCamera(Uri currentPhotoUri) {
        imageView.setImageURI(currentPhotoUri);
    }




}
