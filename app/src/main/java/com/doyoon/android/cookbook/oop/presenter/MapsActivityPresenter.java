package com.doyoon.android.cookbook.oop.presenter;

import com.doyoon.android.cookbook.oop.domain.RemoteToiletDAO;
import com.doyoon.android.cookbook.oop.view.MapsActivityView;

/**
 * Created by DOYOON on 6/14/2017.
 */

public interface MapsActivityPresenter {

    void setToiletDAO(RemoteToiletDAO remoteToiletDAO);

    void setView(MapsActivityView mapsActivityView);

    void notifyMapOnReady();
}
