package com.doyoon.android.cookbook.recipe.fragment.example.recycler;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.cookbook.R;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oop_recipe_activity_main);
        startFragment();
    }

    public void startFragment(){
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragment_container, CustomListFragment.getInstance());
        transaction.commit();
    }
}
