package com.verbosetech.fooddude.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.verbosetech.fooddude.Activities.AddAddressActivity;
import com.verbosetech.fooddude.Activities.EditActivity;
import com.verbosetech.fooddude.Models.Profile;
import com.verbosetech.fooddude.Others.ProfileAdapter;
import com.verbosetech.fooddude.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 28/6/17.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private ProfileAdapter adapter;
    private RecyclerView recyclerView;
    private List<Profile> profileList;

    private ImageView edit;
    private TextView add;
    private TextView save;
    private EditText name;
    private EditText mail;
    private EditText phone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, container, false);

        edit=(ImageView) view.findViewById(R.id.edit);
        add=(TextView) view.findViewById(R.id.add_new);
        name=(EditText) view.findViewById(R.id.name);
        mail=(EditText) view.findViewById(R.id.gmail);
        phone=(EditText) view.findViewById(R.id.phone_num);
        save=(TextView) view.findViewById(R.id.save);


        edit.setOnClickListener(this);
        add.setOnClickListener(this);
        save.setOnClickListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.profile_grid);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getCards();

        return view;
    }

    private void getCards(){

        profileList=new ArrayList<>();

        //fill the cards with saved address(Data from JSON API will be received)

        profileList.add(new Profile("Home","225, Chris Tower E Smallsys INC 795 Dragram,Tuscon AZ-85705,USA"));
        profileList.add(new Profile("Office","225, Chris Tower E Smallsys INC 795 Dragram,Tuscon AZ-85705,USA"));

        adapter=new ProfileAdapter(getActivity(), profileList, new ProfileAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {

                //perform card click functionality

            }

            @Override
            public void onEditClick() {


                startActivity(new Intent(getActivity(), EditActivity.class));
                //intent to edit class activity
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.edit:

                //enabling the edit text fields
                name.setEnabled(true);
                mail.setEnabled(true);
                phone.setEnabled(true);
                save.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
                break;

                //disabling the edit text fields
            case R.id.save:name.setEnabled(false);
                mail.setEnabled(false);
                phone.setEnabled(false);
                save.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);
                break;

            case R.id.add_new:startActivity(new Intent(getActivity(), AddAddressActivity.class));
                break;
        }
    }
}
