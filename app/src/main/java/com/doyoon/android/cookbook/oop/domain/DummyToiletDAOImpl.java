package com.doyoon.android.cookbook.oop.domain;

import com.doyoon.android.cookbook.oop.domain.toilet.Toilet;

import java.util.List;

/**
 * Created by DOYOON on 6/14/2017.
 */

public class DummyToiletDAOImpl implements RemoteToiletDAO{

    @Override
    public List<Toilet> getToiletList(Callback obj) {
        obj.getUrlString();
        return null;
    }
}
