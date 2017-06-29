package com.example.verbosetech.fooddude.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.verbosetech.fooddude.Models.ItemVariety;
import com.example.verbosetech.fooddude.Others.CartCardAdapter;
import com.example.verbosetech.fooddude.Others.FoodVarietyAdapter;
import com.example.verbosetech.fooddude.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class CartActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout button_layout;
    private RecyclerView.LayoutManager layoutManager;
    CartCardAdapter adapter;
    RecyclerView recyclerView;
    List<ItemVariety> cartList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        button_layout=(LinearLayout)findViewById(R.id.button_layout);
        button_layout.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.cart_card_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getCartCards();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_layout:startActivity(new Intent(CartActivity.this,ConfirmActivity.class));
        }
    }

    public void getCartCards(){

        cartList=new ArrayList<>();

        cartList.add(new ItemVariety(R.drawable.pizza1,"14.99 $","Crispy Chicken garlic periperi pizza"));
        cartList.add(new ItemVariety(R.drawable.pizza1,"14.99 $","Crispy Chicken garlic periperi pizza"));
        cartList.add(new ItemVariety(R.drawable.pizza1,"14.99 $","Crispy Chicken garlic periperi pizza"));

        adapter=new CartCardAdapter(getApplicationContext(), cartList, new CartCardAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {



            }
        });
        recyclerView.setAdapter(adapter);

    }
}
