package com.example.verbosetech.fooddude.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.verbosetech.fooddude.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 29/6/17.
 */

public class FoodFragment extends Fragment {


    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_food_layout,container,false);

        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        createViewPager(viewPager);

        tabLayout = (TabLayout)view.findViewById(R.id.tab_host);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons();
        return view;
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new FoodTypeFragment(), "Periperi Cheese");
        adapter.addFrag(new FoodTypeFragment(), "El classico cheese");
        adapter.addFrag(new FoodTypeFragment(), "4 periperi cheese");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void createTabIcons() {

        tabLayout.getTabAt(0).setText("Periperi Cheese");

        tabLayout.getTabAt(1).setText("El classico cheese");

        tabLayout.getTabAt(2).setText("4 periperi cheese");
    }

}
