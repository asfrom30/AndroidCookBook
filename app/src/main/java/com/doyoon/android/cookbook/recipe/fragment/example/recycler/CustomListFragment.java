package com.doyoon.android.cookbook.recipe.fragment.example.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.doyoon.android.cookbook.R;
import com.doyoon.android.cookbook.recipe.fragment.RecyclerFragment;
import com.doyoon.android.cookbook.res.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOYOON on 7/4/2017.
 */

/*
*   Version 0.0.1
*   Listener Check complete...
* */

public class CustomListFragment extends RecyclerFragment<User> {

    public static CustomListFragment instance;

    public static CustomListFragment getInstance(){
        if (instance == null) {
            instance = new CustomListFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        // todo 이름을 조금 정리해봅시다... 클린코드를 적용해서 이름
        // 파이어베이스에 적용...
        // 이 프래그먼트에 다른 버튼이나 그런것들 넣어서 되나 확인 super를 옮겨야 한다 다른 곳으로...
        // item 클릭해서 detail로 넘어가보기.. .넘어가지나...
        // 프래그먼트간 이동도 해보기...
        return view;
    }

    @Override
    public CustomViewHolder throwCustomViewHolder(View view) {
        return new CustomViewHolder(view) {
            TextView textViewName;
            TextView textViewAge;

            @Override
            public void updateRecyclerItemView(View view, User user) {

                textViewName.setText(user.getName());
                textViewAge.setText(user.getAge() + "");
            }

            @Override
            public void dependencyInjection(View itemView, User user) {
                textViewName = (TextView) itemView.findViewById(R.id.textview_name);
                textViewAge = (TextView) itemView.findViewById(R.id.textview_age);

                textViewName.setOnClickListener(this);
                textViewAge.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

                User user = getT();
                Log.e("TAG", v.getId() + "");
                Log.e("TAG", user.toString());
                switch (v.getId()) {
                    case R.id.textview_name :
                        Log.e("TAG", user.getName() + "이름입니다. ");
                        Toast.makeText(getActivity(), user.getName() + "이름입니다. ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.textview_age :
                        Log.e("TAG", user.getAge() + "나이입니다. ");
                        Toast.makeText(getActivity(), user.getAge() + "나이입니다.. ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    @Override
    public int throwItemLayoutId() {
        return R.layout.oop_recipe_item_user;
    }

    @Override
    public int throwFragmentLayoutResId() {
        return R.layout.oop_recipe_fragment_recycler;
    }

    @Override
    public int throwRecyclerViewResId() {
        return R.id.main_recyclerView_bbs_list;
    }

    @Override
    public List<User> throwDataList() {
        // Create Dummy Data List
        List<User> userList = new ArrayList();
        for(int i = 0; i <100; i++) {
            userList.add(new User("김도윤", i));
        }
        return userList;
    }


}