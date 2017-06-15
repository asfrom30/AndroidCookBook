package com.doyoon.android.cookbook.oop.domain;

import com.doyoon.android.cookbook.oop.domain.toilet.Toilet;

import java.util.List;

/**
 * Created by DOYOON on 6/13/2017.
 */

public class RemoteToiletDAOImpl implements RemoteToiletDAO {

    private static RemoteToiletDAO instance;

    public static RemoteToiletDAO getInstance(){
        if (instance == null) {
            instance = new RemoteToiletDAOImpl();
        }
        return instance;
    }

    /* Constructor */
    private RemoteToiletDAOImpl() {

    }

    @Override
    public List<Toilet> getToiletList(Callback obj) {

        return null;
    }

    public static void getData(final Callback obj) {

    }

}
