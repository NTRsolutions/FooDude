package com.example.verbosetech.fooddude.Others;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.verbosetech.fooddude.Models.DiscountItem;
import com.example.verbosetech.fooddude.R;

import java.util.List;

/**
 * Created by sagar on 28/6/17.
 */

public class DiscountItemAdapter extends RecyclerView.Adapter<DiscountItemAdapter.MyHolder>{

    public RecyclerView re;
    private List<DiscountItem> dataSet ;
    public Context context=null;
    VenueAdapterClickCallbacks venueAdapterClickCallbacks;

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        TextView discount;
        ImageView image;

        public MyHolder(View itemView)
        {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.item_name);
            this.price = (TextView) itemView.findViewById(R.id.price);
            this.discount=(TextView)itemView.findViewById(R.id.discount);
            this.image=(ImageView)itemView.findViewById(R.id.image);
        }
    }

    public DiscountItemAdapter(Context c, List<DiscountItem> data, VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discount_item_layout, parent, false);
        MyHolder myNewsHolder=new MyHolder(view);
        re = (RecyclerView) parent.findViewById(R.id.discount_recycle_grid);
        return myNewsHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        TextView name = holder.name;
        TextView price = holder.price;
        TextView discount = holder.discount;
        ImageView image=holder.image;
        name.setText(dataSet.get(position).getName());

        price.setText(dataSet.get(position).getPrice());
        discount.setText(dataSet.get(position).getDiscount());
        image.setImageResource(dataSet.get(position).getImage());

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


