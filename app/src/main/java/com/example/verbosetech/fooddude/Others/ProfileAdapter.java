package com.example.verbosetech.fooddude.Others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.verbosetech.fooddude.Models.PastOrders;
import com.example.verbosetech.fooddude.Models.Profile;
import com.example.verbosetech.fooddude.R;

import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyHolder> {

    public RecyclerView re;
    private List<Profile> dataSet ;
    public Context context=null;
    VenueAdapterClickCallbacks venueAdapterClickCallbacks;



    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView address_type;
        TextView address;


        public MyHolder(View itemView)
        {
            super(itemView);
            this.address_type = (TextView) itemView.findViewById(R.id.address_type);
            this.address = (TextView) itemView.findViewById(R.id.address);
        }
    }

    public ProfileAdapter(Context c, List<Profile> data, VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_address_layout, parent, false);
        MyHolder myNewsHolder=new MyHolder(view);
        re = (RecyclerView) parent.findViewById(R.id.profile_grid);
        return myNewsHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        TextView type = holder.address_type;
        TextView address = holder.address;

        type.setText(dataSet.get(position).getAdd_type());
        address.setText(dataSet.get(position).getAddress());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                venueAdapterClickCallbacks.onCardClick(dataSet.get(position).getAdd_type());

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface VenueAdapterClickCallbacks {

        void onCardClick(String p);
    }


}

