package com.doyoon.android.cookbook.mvp.strict.view;

import android.app.Activity;
import android.widget.Button;

import com.doyoon.android.cookbook.R;
import com.doyoon.android.cookbook.mvp.strict.presenter.StrictMvpMainPresenter;

/**
 * Created by DOYOON on 6/4/2017.
 */

public class StrictMvpMainViewImpl extends StrictMvpMainView {


    public StrictMvpMainViewImpl(Activity activity, StrictMvpMainPresenter presenter) {
        super(activity, presenter);
    }

    @Override
    public void initLayout() {
        this.activity.setContentView(R.layout.activity_strict_mvp_main_impl);
    }

    @Override
    public void initComponent() {
        //TODO 이게 SUPER에 선언 되는게 맞는건가.. 여기에 선언되는게 맞는건가?
        //TODO 변수가 무엇이 있는지.. 알수가 없네...
        this.goListBtn = (Button) this.activity.findViewById(R.id.strictMvpActivity_btn_goList);
        this.saveBtn = (Button) this.activity.findViewById(R.id.strictMvpActivity_btn_save);
    }

    @Override
    public void addListener() {

    }
}
