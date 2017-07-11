package com.example.verbosetech.fooddude.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verbosetech.fooddude.Activities.FoodActivity;
import com.example.verbosetech.fooddude.Activities.FoodDetailActivity;
import com.example.verbosetech.fooddude.Activities.MainActivity;
import com.example.verbosetech.fooddude.Models.DiscountItem;
import com.example.verbosetech.fooddude.Models.Item;
import com.example.verbosetech.fooddude.Others.CustomItemAdapter;
import com.example.verbosetech.fooddude.Others.CustomPagerAdapter;
import com.example.verbosetech.fooddude.Others.DiscountItemAdapter;
import com.example.verbosetech.fooddude.Others.Pager;
import com.example.verbosetech.fooddude.Others.PrefManager;
import com.example.verbosetech.fooddude.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.tag;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by sagar on 28/6/17.
 */

public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    View view;
    Pager mViewPager;
    private CustomPagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private RecyclerView horizontal_recycler_view;
    private ArrayList<Item> horizontalList;
    private CustomItemAdapter horizontalAdapter;


    private RecyclerView.LayoutManager layoutManager;
    DiscountItemAdapter adapter;
    RecyclerView recyclerView;
    List<DiscountItem> itemList;

    LinearLayout linearLayout;
    CardView card;
    TextView city,street;

    int item[]={R.drawable.pizza,R.drawable.main_course,R.drawable.burger,R.drawable.chinese,R.drawable.soup};

    int PLACE_PICKER_REQUEST = 1;
    PrefManager pref;

    int[] mResources = {R.drawable.banerburger, R.drawable.banerburger, R.drawable.banerburger,R.drawable.banerburger};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_home,container,false);
        mViewPager = (Pager) view.findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
        linearLayout=(LinearLayout)view.findViewById(R.id.layout_search);
        card=(CardView)view.findViewById(R.id.card_search);
        linearLayout.setOnClickListener(this);

        city=(TextView)view.findViewById(R.id.city);
        street=(TextView)view.findViewById(R.id.street);
        pref=new PrefManager(getActivity());

        //intialising the horizontal recycle view
        horizontal_recycler_view= (RecyclerView) view.findViewById(R.id.horizontal_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        setAdapter();

        recyclerView = (RecyclerView) view.findViewById(R.id.discount_recycle_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        setDiscountCards();

        //setting the adapter the image's viewpager
        mAdapter = new CustomPagerAdapter(getActivity(), mResources);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
        setPageViewIndicator();




        return view;
    }

    private void setPageViewIndicator() {

        //setting the dots for image's view pager

        Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selected_item_dot));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Log.d("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selected_item_dot));

        if (position + 1 == dotsCount) {

        } else {

        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void setAdapter(){

        horizontalList=new ArrayList<>();

        //filling the data in horizontal recycle view of home page
        horizontalList.add(new Item(item[0],"Pizza"));
        horizontalList.add(new Item(item[1],"Meal Combo"));
        horizontalList.add(new Item(item[2],"Burger"));
        horizontalList.add(new Item(item[3],"Chinese"));
        horizontalList.add(new Item(item[4],"Soup"));

        horizontalAdapter=new CustomItemAdapter(getActivity(), horizontalList, new CustomItemAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {

                pref.setItem(p);
                startActivity(new Intent(getActivity(), FoodActivity.class));
            }
        });

        horizontal_recycler_view.setAdapter(horizontalAdapter);
    }

    public void setDiscountCards(){

        itemList=new ArrayList<>();

        //filling the data in discount recycle view
        itemList.add(new DiscountItem(R.drawable.pizza1,"14.99 $","Crispy Chicken garlic periperi pizza","50% discount"));
        itemList.add(new DiscountItem(R.drawable.pizza2,"12.99 $","Paneer crispy hot veg periperi pizza","50% discount"));

        adapter=new DiscountItemAdapter(getActivity(), itemList, new DiscountItemAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String p) {

                startActivity(new Intent(getActivity(), FoodDetailActivity.class));
            }
        });

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.layout_search:
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
//                String toastMsg = String.format("Place: %s", place.getName());
                String add[]=place.getAddress().toString().split(",");
                if(add.length>=4) {
                    city.setText(add[add.length - 3]);
                    String str = add[add.length - 4];
                    if (str.length() >= 15)
                        street.setText(" " + add[add.length - 4].substring(0, 15) + ".");
                    else
                        street.setText(" " + add[add.length - 4]);
                }
                else
                {
                    city.setText(place.getName());
                    street.setText(place.getName());
                }
            }
        }
    }
}
