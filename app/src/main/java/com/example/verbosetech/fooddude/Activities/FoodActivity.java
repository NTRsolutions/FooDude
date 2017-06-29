package com.example.verbosetech.fooddude.Activities;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.example.verbosetech.fooddude.Fragments.FoodFragment;
import com.example.verbosetech.fooddude.Models.ItemData;
import com.example.verbosetech.fooddude.Others.SpinnerAdapter;
import com.example.verbosetech.fooddude.R;

import java.util.ArrayList;

import static com.example.verbosetech.fooddude.R.id.spinner;

/**
 * Created by sagar on 29/6/17.
 */

public class FoodActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        getToolbarSpinner();

        Fragment fragment = new FoodFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commitAllowingStateLoss();

    }

    public void getToolbarSpinner(){

        ArrayList<ItemData> list=new ArrayList<>();

        list.add(new ItemData("Pizza",R.drawable.pizza_wht));
        list.add(new ItemData("Meal Combo",R.drawable.main_course_wht));
        list.add(new ItemData("Burger",R.drawable.burger_wht));
        list.add(new ItemData("Soup",R.drawable.soup_wht));
        list.add(new ItemData("Chinese",R.drawable.chinese_wht));

        Spinner sp=(Spinner)toolbar.findViewById(spinner);
        SpinnerAdapter adapter=new SpinnerAdapter(this,
                R.layout.spinner_layout,R.id.food_name,list);
        sp.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        sp.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}