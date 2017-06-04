package com.doyoon.android.cookbook.mvp.comport.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.doyoon.android.cookbook.R;

/**
 * Created by DOYOON on 6/4/2017.
 */


public class ComportMvpListView {

    Activity activity;
    RecyclerView recyclerView;

    public ComportMvpListView(Activity activity) {

        this.activity = activity;

        /* Layout */
        activity.setContentView(R.layout.activity_comport_mvp_list);

        /* Dependecy Injection */
        this.recyclerView = (RecyclerView) activity.findViewById(R.id.comportMvpList_rv);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.activity));
    }
}
