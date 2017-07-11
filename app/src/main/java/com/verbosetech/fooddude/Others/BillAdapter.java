package com.verbosetech.fooddude.Others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.verbosetech.fooddude.Models.BillItem;

import com.verbosetech.fooddude.R;

import java.util.List;

/**
 * Created by sagar on 1/7/17.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyHolder> {

    public RecyclerView re;
    private List<BillItem> dataSet ;
    public Context context=null;
    VenueAdapterClickCallbacks venueAdapterClickCallbacks;

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;

        public MyHolder(View itemView)
        {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.item_name);
            this.price = (TextView) itemView.findViewById(R.id.price);

        }
    }

    public BillAdapter(Context c, List<BillItem> data, VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_item_layout, parent, false);
        MyHolder myNewsHolder=new MyHolder(view);
        re = (RecyclerView) parent.findViewById(R.id.bill_item_grid);
        return myNewsHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        TextView name = holder.name;
        TextView price = holder.price;

        name.setText(dataSet.get(position).getName()+" "+dataSet.get(position).getQuantity());

        String s=dataSet.get(position).getQuantity();

        int q=Integer.parseInt(s.substring(2,s.length()-1));

        price.setText(dataSet.get(position).getPerprice()*q+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                venueAdapterClickCallbacks.onCardClick(dataSet.get(position).getName());

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface VenueAdapterClickCallbacks {
        void onCardClick( String p);

    }
}

