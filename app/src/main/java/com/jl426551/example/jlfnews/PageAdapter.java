package com.jl426551.example.jlfnews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.jl426551.example.jlfnews.DataUtilities.CategoryUtilities;

import java.util.ArrayList;

public class PageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> tabTitles;
    private ArrayList<Integer> tabIndexes;
    private int searchEngineSelection;

    //Default Constructor.
    public PageAdapter(Context context, FragmentManager manager, int searchOption) {

        super(manager);
        Log.v("PageAdapter", "creator " + searchOption);

        searchEngineSelection = searchOption;
        tabTitles = CategoryUtilities.getDefaultTabTitle(context);
        tabIndexes = CategoryUtilities.getIndeces();
    }

    @Override
    public Fragment getItem(int position) {

        Log.v("PAdpt", "getItem");
        Log.v("PAdpt", "searchEngineSelection as " + searchEngineSelection);
        //NewsFragment returnFragment = new NewsFragment();
        NewsFragment returnFragment = new NewsFragment();
        int newsIndex = tabIndexes.get(position);
        returnFragment.addIndex(newsIndex);
        returnFragment.addEngine(searchEngineSelection);

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
