package com.doyoon.android.cookbook.mvp.strict.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.doyoon.android.cookbook.R;
import com.doyoon.android.cookbook.mvp.strict.presenter.StrictMvpMainPresenter;

/**
 * Created by DOYOON on 6/4/2017.
 */

// TODO abstract로 하니까 여기가 애매해지네... OnclickListner를 부모클래스에서 정의해도 되는데...
    // 잘 눈에 들어오지가 않음..(코드가 관심사에 의해서 분류되는 것이 아니라 오히려 더 난잡해짐.. ㅠㅠ)
public abstract class StrictMvpMainView implements View.OnClickListener {

    Activity activity;
    StrictMvpMainPresenter presenter;

    Button goListBtn, saveBtn;

    public abstract void initLayout();
    public abstract void initComponent();
    public abstract void addListener();

    public StrictMvpMainView(Activity activity, StrictMvpMainPresenter presenter) {
        this.activity = activity;
        this.presenter = presenter;

        this.initLayout();
        this.initComponent();
        this.addListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.strictMvpActivity_btn_goList:
                this.presenter.onGoListBtn();
                break;
            case R.id.strictMvpActivity_btn_save:
                this.presenter.onSaveBtn();
                break;
        }
    }



}
