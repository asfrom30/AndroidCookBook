package com.doyoon.android.cookbook.mvp.strict.presenter;

import android.app.Activity;

/**
 * Created by DOYOON on 6/4/2017.
 */

public class StrictMvpMainPresenterImpl implements StrictMvpMainPresenter{

    private Activity activity;

    public StrictMvpMainPresenterImpl(Activity activity) {

        //TODO 접근이 안되네....
        this.activity = activity;
    }

    @Override
    public void onGoListBtn() {
//        Intent intent = new Intent(this.activity.getBaseContext(), StrictMvpListActivity.class);
//        this.activity.startActivity(intent);
    }

    @Override
    public void onSaveBtn() {

    }
}
