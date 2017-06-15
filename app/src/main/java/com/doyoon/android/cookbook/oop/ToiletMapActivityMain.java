package com.doyoon.android.cookbook.oop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.cookbook.oop.domain.RemoteToiletDAO;
import com.doyoon.android.cookbook.oop.domain.RemoteToiletDAOImpl;
import com.doyoon.android.cookbook.oop.presenter.MapsActivityPresenter;
import com.doyoon.android.cookbook.oop.presenter.MapsActivityPresenterImpl;
import com.doyoon.android.cookbook.oop.view.MapsActivityView;

public class ToiletMapActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Presenter 생성 */
        MapsActivityPresenter presenter = new MapsActivityPresenterImpl();

        /* View 생성*/
        MapsActivityView mapsActivityView = new MapsActivityView(this, presenter);
        presenter.setView(mapsActivityView);

        /* Domain 생성 및 상호참조 */
        // 인터페이스를 상속받아서 느슨한 결합..
//         RemoteToiletDAO dao = DummyToiletDAOImp.getInstance();// 더미테스트를 할 수 있다.
        RemoteToiletDAO dao = RemoteToiletDAOImpl.getInstance();
        presenter.setToiletDAO(dao);
    }
}
