package com.jl426551.example.jlfnews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jl426551.example.jlfnews.DataUtilities.CategoryUtilities;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<String> tabTitles;
    private ArrayList<Integer> tabIndexes;

    //Default Constructor. Assumes reader has all categories enabled.
    public PageAdapter(Context context, FragmentManager manager) {
        super(manager);

        this.context = context;
        tabTitles = CategoryUtilities.getDefaultTabTitle(context);
        tabIndexes = CategoryUtilities.getIndeces();
    }

    /*
    //Constructor which include a list of which categories are chosen to display.
    public PageAdapter(Context context, FragmentManager manager, int[] preferences) {
        super(manager);
        this.context = context;

        tabTitles = DataUtilities.getSelectedTabs(context, preferences);
        tabIndexes = DataUtilities.getSelectedIndices(preferences);
    } */

    @Override
    public Fragment getItem(int position) {

        //NewsFragment returnFragment = new NewsFragment();
        StoryFragment returnFragment = new StoryFragment();

        int newsIndex = tabIndexes.get(position);
        returnFragment.addIndex(newsIndex);

        return returnFragment;
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
