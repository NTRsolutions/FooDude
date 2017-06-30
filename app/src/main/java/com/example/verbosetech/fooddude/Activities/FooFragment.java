package com.example.verbosetech.fooddude.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.verbosetech.fooddude.Models.Variety;
import com.example.verbosetech.fooddude.Others.CustomItemAdapter;
import com.example.verbosetech.fooddude.Others.CustomVarietyAdapter;
import com.example.verbosetech.fooddude.R;

import java.util.ArrayList;

/**
 * Created by sagar on 30/6/17.
 */

public class FooFragment extends Fragment {

    private View view;
    private RecyclerView horizontal_recycler_view;
    private ArrayList<Variety> horizontalList;
    private CustomVarietyAdapter horizontalAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.test,container,false);

        horizontal_recycler_view= (RecyclerView) view.findViewById(R.id.horizontal_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        setAdapter();

        return view;
    }

    public void setAdapter(){

        horizontalList=new ArrayList<>();
        horizontalList.add(new Variety("PERIPERI CHEESE"));
        horizontalList.add(new Variety("EL CLASSICO CHEESE"));
        horizontalList.add(new Variety("4 PERIPERI CHEESE"));

        horizontalAdapter=new CustomVarietyAdapter(getActivity(), horizontalList, new CustomVarietyAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {


            }
        });

        horizontal_recycler_view.setAdapter(horizontalAdapter);
    }
}
