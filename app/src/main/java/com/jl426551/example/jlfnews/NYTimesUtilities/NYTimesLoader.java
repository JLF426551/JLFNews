package com.jl426551.example.jlfnews.NYTimesUtilities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.jl426551.example.jlfnews.BingUtilities.News;

import java.util.ArrayList;

public class NYTimesLoader extends AsyncTaskLoader<ArrayList<News>> {

    String query;

    public NYTimesLoader(Context context, String query) {
        super(context);
        this.query = query;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Nullable
    @Override
    public ArrayList<News> loadInBackground() {
        return NYTimesSearchClass.search(query);
    }
}
