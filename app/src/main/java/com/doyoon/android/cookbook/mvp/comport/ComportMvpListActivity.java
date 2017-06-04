package com.doyoon.android.cookbook.mvp.comport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.doyoon.android.cookbook.mvp.comport.domain.Loader;
import com.doyoon.android.cookbook.mvp.comport.domain.Memo;
import com.doyoon.android.cookbook.mvp.comport.presenter.ComportMvpListRvAdapter;
import com.doyoon.android.cookbook.mvp.comport.view.ComportMvpListView;

import java.util.ArrayList;

/* separated between Activity and View(viewHolder)*/
public class ComportMvpListActivity extends AppCompatActivity {

    private ArrayList<Memo> memoList;
    private ComportMvpListView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* View를 분리한다. */
        this.view = new ComportMvpListView(this);

        // 데이터를 가져온다.
        memoList = Loader.getMemoList();

        /* 아이템에 대한 MVP
            사용할 Recylcer View도 마찬가지로 MVP로 구현한다.
            Recylcer View는 Item 이지만 MVP로 구현하여 로직과 뷰를 분리한다.
            여기서는 Adapter가 Presenter 역할을 한다.
            RecyclerView와 ViewHolder를 분리할지 inner class로 넣을지는 선택의 문제. */

        // 아답터를 생성
        RecyclerView.Adapter adapter = new ComportMvpListRvAdapter(memoList);

        // 아답터를 세팅
        this.view.setAdapter(adapter);
    }
}
