package com.example.verbosetech.fooddude.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.verbosetech.fooddude.Models.ItemVariety;
import com.example.verbosetech.fooddude.Others.CartCardAdapter;
import com.example.verbosetech.fooddude.Others.FoodVarietyAdapter;
import com.example.verbosetech.fooddude.R;

import java.text.DecimalFormat;
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
    TextView subtotal,service_tax,delivery_charge,total;

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        button_layout=(LinearLayout)findViewById(R.id.button_layout);
        button_layout.setOnClickListener(this);
        subtotal=(TextView)findViewById(R.id.subtotal);
        service_tax=(TextView)findViewById(R.id.service_tax);
        delivery_charge=(TextView)findViewById(R.id.delivery_charge);
        total=(TextView)findViewById(R.id.total);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        toolbar.setTitle("Cart");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

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

        cartList.add(new ItemVariety(R.drawable.pizza1,"14.99","Crispy Chicken garlic periperi pizza"));
        cartList.add(new ItemVariety(R.drawable.pizza2,"12.80","Paneer crispy hot veg periperi pizza"));


        adapter=new CartCardAdapter(getApplicationContext(), cartList, new CartCardAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {

                    double price=Double.parseDouble(p);
                    double tax=0.04*price;
                    DecimalFormat numberFormat = new DecimalFormat("#.00");

                    subtotal.setText(numberFormat.format(price)+" $");
                    service_tax.setText(numberFormat.format(tax)+" $");
                    delivery_charge.setText(0.50+" $");
                    price=price+tax+0.50;
                    total.setText(numberFormat.format(price)+" $");

            }
        });
        recyclerView.setAdapter(adapter);

    }
}
