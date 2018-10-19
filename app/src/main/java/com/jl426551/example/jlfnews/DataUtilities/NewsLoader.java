package com.jl426551.example.jlfnews.DataUtilities;

import android.content.Context;
import android.util.Log;

import com.jl426551.example.jlfnews.SearchClasses.BingSearchClass;
import com.jl426551.example.jlfnews.SearchClasses.StorySearchClass;
import com.jl426551.example.jlfnews.SearchClasses.NYTimesSearchClass;
import com.jl426551.example.jlfnews.SearchClasses.NewsApiSearchClass;

import java.util.ArrayList;

public class NewsLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<News>> {

    public static final int API_NEWS_LOADER = 100;
    public static final int BING_LOADER = 200;
    public static final int GUARDIAN_LOADER = 300;
    public static final int NYT_LOADER = 400;

    private String query;
    int selection;

    public NewsLoader(Context context, int service, String query) {

        super(context);
        selection = service;
        this.query = query;

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public ArrayList<News> loadInBackground() {

        switch (selection) {
            case GUARDIAN_LOADER:
                Log.v("NewsLoader", "calling guardian");
                return StorySearchClass.search(query);
            case NYT_LOADER:
                Log.v("NewsLoader", "calling NYT");
                return NYTimesSearchClass.search(query);
            case API_NEWS_LOADER:
                Log.v("NewsLoader", "calling News API");
                return NewsApiSearchClass.search(query);
            case BING_LOADER:
            default:
                Log.v("NewsLoader", "calling Bing");
                return BingSearchClass.search(query);
        }
    }

}