package com.doyoon.android.cookbook.mvp.strict;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.cookbook.mvp.strict.presenter.StrictMvpMainPresenter;
import com.doyoon.android.cookbook.mvp.strict.presenter.StrictMvpMainPresenterImpl;
import com.doyoon.android.cookbook.mvp.strict.view.StrictMvpMainView;
import com.doyoon.android.cookbook.mvp.strict.view.StrictMvpMainViewImpl;

public class StrictMvpMainActivityImpl extends AppCompatActivity implements StrictMvpMainActivity{

    StrictMvpMainPresenter presenter;
    StrictMvpMainView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new StrictMvpMainPresenterImpl(this);
        this.view = new StrictMvpMainViewImpl(this, this.presenter);
    }
}
