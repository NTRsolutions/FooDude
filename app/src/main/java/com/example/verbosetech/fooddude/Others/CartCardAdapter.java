package com.example.verbosetech.fooddude.Others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.verbosetech.fooddude.Models.ItemVariety;
import com.example.verbosetech.fooddude.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class CartCardAdapter extends RecyclerView.Adapter<CartCardAdapter.MyHolder> {

    public RecyclerView re;
    private List<ItemVariety> dataSet ;
    public Context context=null;
    VenueAdapterClickCallbacks venueAdapterClickCallbacks;
    PrefManager pref;
    double amount=0.0;



    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price,no_of_items;
        ImageView image,add,remove;


        public MyHolder(View itemView)
        {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.food_name);
            this.price = (TextView) itemView.findViewById(R.id.price);
            this.image=(ImageView)itemView.findViewById(R.id.image);
            this.add=(ImageView)itemView.findViewById(R.id.add);
            this.remove=(ImageView)itemView.findViewById(R.id.remove);
            this.no_of_items=(TextView)itemView.findViewById(R.id.no_of_items);
        }
    }

    public CartCardAdapter(Context c, List<ItemVariety> data, VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;
        pref=new PrefManager(context);

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
        final TextView price = holder.price;
        ImageView image=holder.image;
        final TextView no_of_items=holder.no_of_items;
        ImageView add=holder.add;
        ImageView remove=holder.remove;

        name.setText(dataSet.get(position).getName());
        ImageSpan is = new ImageSpan(context, R.drawable.non_veg);
        SpannableString texts = new SpannableString(name.getText().toString().concat("   "));
        texts.setSpan(is,texts.length()-1,texts.length(),0);
        name.setText(texts);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cal_dist=0,d;
                String a=no_of_items.getText().toString();
                d=Integer.parseInt(a);
                cal_dist=++d;
                no_of_items.setText(cal_dist+"");
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                price.setText(numberFormat.format(cal_dist*Double.parseDouble(dataSet.get(position).getPrice()))+" $");
                amount= amount + Double.parseDouble(dataSet.get(position).getPrice());

                venueAdapterClickCallbacks.onCardClick(amount+"");
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cal_dist=1,d;
                String a=no_of_items.getText().toString();
                d=Integer.parseInt(a);
                if(d>1){
                    cal_dist=--d;
                    amount= amount - Double.parseDouble(dataSet.get(position).getPrice());}
                no_of_items.setText(cal_dist+"");
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                price.setText(numberFormat.format(cal_dist*Double.parseDouble(dataSet.get(position).getPrice()))+" $");


                venueAdapterClickCallbacks.onCardClick(amount+"");
            }
        });

        price.setText(dataSet.get(position).getPrice()+" $");
        image.setImageResource(dataSet.get(position).getImage());

        amount= amount + Double.parseDouble(dataSet.get(position).getPrice());


        venueAdapterClickCallbacks.onCardClick(amount+"");




    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface VenueAdapterClickCallbacks {
        void onCardClick( String p);

    }
}
