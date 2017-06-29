package com.example.verbosetech.fooddude.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.verbosetech.fooddude.R;

/**
 * Created by sagar on 29/6/17.
 */

public class FoodDetailActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout button_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details_layout);

        button_layout=(LinearLayout)findViewById(R.id.button_layout);
        button_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_layout:startActivity(new Intent(FoodDetailActivity.this,CartActivity.class));

        }
    }
}
