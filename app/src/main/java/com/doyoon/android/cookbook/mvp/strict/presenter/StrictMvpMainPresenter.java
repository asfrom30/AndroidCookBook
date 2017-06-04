package com.doyoon.android.cookbook.mvp.strict.presenter;

import android.app.Activity;

/**
 * Created by DOYOON on 6/4/2017.
 */

public interface StrictMvpMainPresenter {

    // 이렇게 명시적으로 값을 null을 넣어주는게 맞는건가??
    public Activity activity = null;

    public void onGoListBtn();
    public void onSaveBtn();

}
