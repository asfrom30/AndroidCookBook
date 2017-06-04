package com.doyoon.android.cookbook.mvp.comport.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.doyoon.android.cookbook.R;
import com.doyoon.android.cookbook.mvp.comport.ComportMvpActivityMain;

/**
 * Created by DOYOON on 6/4/2017.
 */

public class ComportMvpAcitivityView implements View.OnClickListener{

    private  Activity activity;
    private ComportMvpActivityMain presenter;

    private  Button goListBtn;
    private  Button onSaveBtn;

    public ComportMvpAcitivityView(Activity activity) {

        /* 객체지향 설계에서는 구현체보다는 부모클래스나 인터페이스에 의존한다. */
        this.activity = activity;
        this.presenter = (ComportMvpActivityMain) activity;

        /* Note : Layout을 생성하기 전에는 의존성주입이 되지 않는다. */
        this.activity.setContentView(R.layout.activity_comport_mvp_main);

        /* Dependency Injection */
        this.goListBtn = (Button) this.activity.findViewById(R.id.comportMvpActivity_btn_goList);
        this.onSaveBtn = (Button) this.activity.findViewById(R.id.comportMvpActivity_btn_save);

        /* Add Listener */
        this.goListBtn.setOnClickListener(this);
        this.onSaveBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.comportMvpActivity_btn_goList):
                /* 2. This code is belong to Logic(Presenter), Move to Logic(presenter) Class */
                // Intent intent = new Intent(v.getContext(), ListActivity.class);
                // this.presenter.startActivity(intent);
                this.presenter.goList();
                break;
            case (R.id.comportMvpActivity_btn_save):
                this.presenter.onBtnSave();
                break;
        }
    }
}
