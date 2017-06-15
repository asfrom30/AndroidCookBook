package com.doyoon.android.cookbook.oop.domain;

import android.app.ProgressDialog;
import android.content.Context;

import com.doyoon.android.cookbook.oop.domain.toilet.Toilet;

import java.util.List;

/**
 * Created by DOYOON on 6/14/2017.
 */

public interface RemoteToiletDAO {
    public List<Toilet> getToiletList(Callback obj);

    public interface Callback {
        public Context getContext();
        public ProgressDialog getProgress();
        public String getUrlString();
        public void postExecute(String jsonString);
    }
}
