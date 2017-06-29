package com.example.verbosetech.fooddude.Others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.verbosetech.fooddude.Models.ItemVariety;
import com.example.verbosetech.fooddude.R;

import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class CartCardAdapter extends RecyclerView.Adapter<CartCardAdapter.MyHolder> {

    public RecyclerView re;
    private List<ItemVariety> dataSet ;
    public Context context=null;
    VenueAdapterClickCallbacks venueAdapterClickCallbacks;

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        ImageView image;

        public MyHolder(View itemView)
        {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.food_name);
            this.price = (TextView) itemView.findViewById(R.id.price);
            this.image=(ImageView)itemView.findViewById(R.id.image);
        }
    }

    public CartCardAdapter(Context c, List<ItemVariety> data, VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_cards, parent, false);
        MyHolder myNewsHolder=new MyHolder(view);
        re = (RecyclerView) parent.findViewById(R.id.cart_card_grid);
        return myNewsHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        TextView name = holder.name;
        TextView price = holder.price;
        ImageView image=holder.image;
        name.setText(dataSet.get(position).getName());

        price.setText(dataSet.get(position).getPrice());
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
