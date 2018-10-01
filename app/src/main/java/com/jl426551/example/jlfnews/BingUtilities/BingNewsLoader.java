package com.jl426551.example.jlfnews.BingUtilities;

import android.content.Context;

import java.util.ArrayList;

public class BingNewsLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<News>> {

    private String query;

    public BingNewsLoader(Context context, String query) {
        super(context);
        this.query = query;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        return BingSearchClass.search(query);
    }

}
