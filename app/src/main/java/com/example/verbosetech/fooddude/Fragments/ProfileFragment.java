package com.example.verbosetech.fooddude.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.verbosetech.fooddude.Models.PastOrders;
import com.example.verbosetech.fooddude.Models.Profile;
import com.example.verbosetech.fooddude.Others.PastOrdersAdapter;
import com.example.verbosetech.fooddude.Others.ProfileAdapter;
import com.example.verbosetech.fooddude.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 28/6/17.
 */

public class ProfileFragment extends Fragment {

    private View view;
    private RecyclerView.LayoutManager layoutManager;
    ProfileAdapter adapter;
    RecyclerView recyclerView;
    List<Profile> profileList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.profile_layout,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.profile_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getCards();

        return view;
    }

    public void getCards(){

        profileList=new ArrayList<>();
        profileList.add(new Profile("Home","225, Chris Tower E Smallsys INC 795 Dragram,Tuscon AZ-85705,USA"));
        profileList.add(new Profile("Office","225, Chris Tower E Smallsys INC 795 Dragram,Tuscon AZ-85705,USA"));

        adapter=new ProfileAdapter(getActivity(), profileList, new ProfileAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {

                Toast.makeText(getActivity(),p,Toast.LENGTH_LONG).show();

            }
        });

        recyclerView.setAdapter(adapter);
    }
}
