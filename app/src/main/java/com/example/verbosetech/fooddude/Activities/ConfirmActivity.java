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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.verbosetech.fooddude.Models.BillItem;
import com.example.verbosetech.fooddude.Others.BillAdapter;
import com.example.verbosetech.fooddude.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class ConfirmActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout button_layout;
    Toolbar toolbar;

    private RecyclerView.LayoutManager layoutManager;
    BillAdapter adapter;
    RecyclerView recyclerView;
    List<BillItem> billItemList;
    ImageView edit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_order_layout);

        edit=(ImageView)findViewById(R.id.edit);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        toolbar.setTitle("Confirm Order");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        edit.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.bill_item_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getCards();

        button_layout=(LinearLayout)findViewById(R.id.button_layout);
        button_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

           case R.id.button_layout:startActivity(new Intent(ConfirmActivity.this,PaymentActivity.class));
               //intent to go to payment activity to perform payment
               break;
            case R.id.edit:startActivity(new Intent(ConfirmActivity.this,EditActivity.class));
                //intent to go to payment activity to edit delivery address
                break;
        }
    }

    public void getCards(){

        billItemList=new ArrayList<>();

        //filling bill with the contents purchased(Data from API)

        billItemList.add(new BillItem("Crispy Chicken garlic periperi pizza","(x1)",14.99));
        billItemList.add(new BillItem("Paneer crispy hot veg periperi pizza","(x1)",12.99));

        adapter=new BillAdapter(getApplicationContext(), billItemList, new BillAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {

                Toast.makeText(getApplicationContext(),p,Toast.LENGTH_LONG).show();
                //optional card click function
            }
        });

        recyclerView.setAdapter(adapter);

    }
}
