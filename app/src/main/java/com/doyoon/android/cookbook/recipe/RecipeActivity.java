package com.doyoon.android.cookbook.recipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.doyoon.android.cookbook.R;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        /* device width height를 가져온다. */
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        double deviceWidth = metrics.widthPixels;
        double deviceHeight = metrics.heightPixels;

//        getContent
    }
}
