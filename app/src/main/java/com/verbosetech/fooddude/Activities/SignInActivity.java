package com.verbosetech.fooddude.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.verbosetech.fooddude.R;

/**
 * Created by sagar on 24/6/17.
 */

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView sign_in;
    private TextView sign_up;
    private TextView sign_in_text;
    private TextView sign_up_text;
    private TextView forgot;
    private EditText name;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private Button signIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);
        sign_up=(TextView)findViewById(R.id.sign_up);
        sign_in=(TextView)findViewById(R.id.sign_in);
        sign_in_text=(TextView)findViewById(R.id.sign_in_text);
        sign_up_text=(TextView)findViewById(R.id.sign_up_text);
        forgot=(TextView)findViewById(R.id.forgot);
        name=(EditText)findViewById(R.id.full_name);
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        signIn=(Button)findViewById(R.id.sign_in_button);

        signIn.setOnClickListener(this);

        sign_up.setOnClickListener(this);
        sign_in.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //to turn it into sign up layout

            case R.id.sign_up:

                sign_up_text.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                forgot.setVisibility(View.VISIBLE);


                break;
            case R.id.sign_in:

                //to turn it into sign in layout

                sign_up_text.setVisibility(View.GONE);
                name.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                layout1.setVisibility(View.VISIBLE);
                forgot.setVisibility(View.GONE);
                break;

            case R.id.sign_in_button:startActivity(new Intent(this,MainActivity.class));
                finish();
        }
    }
}
