package com.doyoon.android.cookbook.mvp.comport.domain;

/**
 * Created by DOYOON on 5/29/2017.
 */

/* 데이터 번지수가 지워지지 않는다. */
/* Adapter에서 보고 있는 메모리 번지수가 일정하므로... */

import java.util.ArrayList;

public class Loader {

    public static final ArrayList<Memo> memoList = new ArrayList<>();

    public Loader() {


    }

    public static ArrayList<Memo> getMemoList() {
        /* 이렇게 new를 선언하면 메모리 번지수가 계속 바뀐다. */
        /* Adapter는 새로 바뀐 번지수를 모르게 된다. */
        // ArrayList<Memo> memo = new ArrayList<>();

        /* 따라서 위처럼 static final을 선언하고 clear로 지운다음에 다시 넣는다. */
        memoList.clear();

        for(int i=0; i < 5; i++){
            Memo memo = new Memo();
            memo.setId(i+1+"");
            memo.setTitle("제목" + i);
            memo.setDate(System.currentTimeMillis()+"");
            memo.setContent("내용"+i);

            memoList.add(memo);
        }

        return memoList;
    }
}
