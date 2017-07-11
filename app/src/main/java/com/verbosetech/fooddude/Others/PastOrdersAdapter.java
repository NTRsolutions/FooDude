package com.verbosetech.fooddude.Others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.verbosetech.fooddude.Models.PastOrders;
import com.verbosetech.fooddude.R;

import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class PastOrdersAdapter extends RecyclerView.Adapter<PastOrdersAdapter.MyHolder> {

    public RecyclerView re;
    private List<PastOrders> dataSet ;
    public Context context=null;
    VenueAdapterClickCallbacks venueAdapterClickCallbacks;



    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView date;
        TextView items;
        TextView price;


        public MyHolder(View itemView)
        {
            super(itemView);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.items = (TextView) itemView.findViewById(R.id.number_of_items);
            this.price=(TextView)itemView.findViewById(R.id.price);
        }
    }

    public PastOrdersAdapter(Context c, List<PastOrders> data, VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.past_orders_card, parent, false);
        MyHolder myNewsHolder=new MyHolder(view);
        re = (RecyclerView) parent.findViewById(R.id.past_orders_grid);
        return myNewsHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        TextView date = holder.date;
        TextView items = holder.items;
        TextView price = holder.price;

        date.setText(dataSet.get(position).getDate());
        items.setText(dataSet.get(position).getNumber_of_items());
        price.setText(dataSet.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                venueAdapterClickCallbacks.onCardClick(dataSet.get(position).getDate());

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


