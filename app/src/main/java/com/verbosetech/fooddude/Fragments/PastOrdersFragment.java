package com.verbosetech.fooddude.Fragments;

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

import com.verbosetech.fooddude.Models.PastOrders;
import com.verbosetech.fooddude.Others.PastOrdersAdapter;
import com.verbosetech.fooddude.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class PastOrdersFragment extends Fragment {

    private PastOrdersAdapter adapter;
    private RecyclerView recyclerView;
    private List<PastOrders> ordersList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.past_orders_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.past_orders_grid);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getCards();
        return view;
    }

    private void getCards(){

        ordersList=new ArrayList<>();

        //filling the cards with data(Data from JSON API will be received here)
        ordersList.add(new PastOrders("12 May","3 items ordered","33.54 $"));
        ordersList.add(new PastOrders("08 May","2 items ordered","21.57 $"));
        ordersList.add(new PastOrders("02 May","2 items ordered","24.89 $"));
        ordersList.add(new PastOrders("28 April","5 items ordered","65.21 $"));
        ordersList.add(new PastOrders("21 April","2 items ordered","19.33 $"));

        adapter=new PastOrdersAdapter(getActivity(), ordersList, new PastOrdersAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {

                Toast.makeText(getActivity(),p,Toast.LENGTH_LONG).show();
                //perform the card click functionality
            }
        });
        recyclerView.setAdapter(adapter);
    }


}
