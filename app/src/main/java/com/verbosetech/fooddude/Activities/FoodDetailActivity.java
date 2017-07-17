package com.verbosetech.fooddude.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.verbosetech.fooddude.R;


/**
 * Created by sagar on 29/6/17.
 */

public class FoodDetailActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout button_layout;
    ImageView back,remove,add;
    TextView itemname,no_of_items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details_layout);

        itemname=(TextView)findViewById(R.id.item_name);

        //adding the image of nonveg sign at the end ot the name of pizza

        ImageSpan is = new ImageSpan(getApplicationContext(), R.drawable.non_veg);
        SpannableString texts = new SpannableString(itemname.getText().toString().concat("   "));
        texts.setSpan(is,texts.length() - 1,texts.length(),0);
        itemname.setText(texts);

        remove=(ImageView)findViewById(R.id.remove);
        add=(ImageView)findViewById(R.id.add);
        remove.setOnClickListener(this);
        add.setOnClickListener(this);

        no_of_items=(TextView)findViewById(R.id.no_of_items);


        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);

        button_layout=(LinearLayout)findViewById(R.id.button_layout);
        button_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_layout:startActivity(new Intent(FoodDetailActivity.this,CartActivity.class));
                break;

            case R.id.back:finish();
                break;

            //cases to handle add and remove button functions
            case R.id.remove:
                int cal_dist=0,d;
                String a=no_of_items.getText().toString();
                d=Integer.parseInt(a);
                if(d>=1.0)
                    cal_dist=d;
                no_of_items.setText(cal_dist+"");
                break;

            case R.id.add:
                a=no_of_items.getText().toString();
                d=Integer.parseInt(a);
                cal_dist=++d;
                no_of_items.setText(cal_dist+"");
        }
    }
}