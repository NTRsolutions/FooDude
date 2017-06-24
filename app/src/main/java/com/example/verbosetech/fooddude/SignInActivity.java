package com.example.verbosetech.fooddude;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sagar on 24/6/17.
 */

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    TextView sign_in,sign_up,sign_in_text,sign_up_text;
    EditText name;
    LinearLayout layout1,layout2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);
        sign_up=(TextView)findViewById(R.id.sign_up);
        sign_in=(TextView)findViewById(R.id.sign_in);
        sign_in_text=(TextView)findViewById(R.id.sign_in_text);
        sign_up_text=(TextView)findViewById(R.id.sign_up_text);
        name=(EditText)findViewById(R.id.full_name);
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);

        sign_up.setOnClickListener(this);
        sign_in.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.sign_up:

                sign_up_text.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);


                break;
            case R.id.sign_in:

                sign_up_text.setVisibility(View.GONE);
                name.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                layout1.setVisibility(View.VISIBLE);
                break;
        }
    }
}
