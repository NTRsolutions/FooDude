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

import com.example.verbosetech.fooddude.Models.BillItem;
import com.example.verbosetech.fooddude.Models.DiscountItem;
import com.example.verbosetech.fooddude.Others.BillAdapter;
import com.example.verbosetech.fooddude.Others.DiscountItemAdapter;
import com.example.verbosetech.fooddude.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class ProcessOrdersFragment extends Fragment {

    private View view;
    private RecyclerView.LayoutManager layoutManager;
    BillAdapter adapter;
    RecyclerView recyclerView;
    List<BillItem> billItemList;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.process_layout,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.bill_item_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getCards();

        return view;
    }

    public void getCards(){

        billItemList=new ArrayList<>();
        billItemList.add(new BillItem("Crispy Chicken garlic periperi pizza","(x1)",14.99));
        billItemList.add(new BillItem("Paneer crispy hot veg periperi pizza","(x1)",12.99));

        adapter=new BillAdapter(getActivity(), billItemList, new BillAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {

                Toast.makeText(getActivity(),p,Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(adapter);

    }
}
