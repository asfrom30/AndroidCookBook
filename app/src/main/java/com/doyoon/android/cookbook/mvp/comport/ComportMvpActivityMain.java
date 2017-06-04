package com.doyoon.android.cookbook.mvp.comport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.cookbook.mvp.comport.view.ComportMvpAcitivityView;

public class ComportMvpActivityMain extends AppCompatActivity {

    /* Tip : 가능한 new의 선언을 줄인다.. */
    ComportMvpAcitivityView comportMvpAcitivityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1.1 Layout도 View이므로 코드를 옮긴다.
//      setContentView(R.layout.activity_comport_mvp_main);

        // 1. View를 분리한다.
        comportMvpAcitivityView = new ComportMvpAcitivityView(this);


    }

    public void goList() {
        Intent intent = new Intent(getBaseContext(), ComportMvpListActivity.class);
        this.startActivity(intent);
    }

    public void onBtnSave(){
        /* Something to do */
    }
}
