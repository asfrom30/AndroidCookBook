package com.doyoon.android.cookbook.oop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.cookbook.oop.presenter.MapsActivityPresenter;
import com.doyoon.android.cookbook.oop.presenter.MapsActivityPresenterImpl;
import com.doyoon.android.cookbook.oop.view.MapsActivityView;

public class MapActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Presenter 생성 */
        MapsActivityPresenter presenter = new MapsActivityPresenterImpl();

        /* View 생성*/
        new MapsActivityView(this, presenter);
    }
}
