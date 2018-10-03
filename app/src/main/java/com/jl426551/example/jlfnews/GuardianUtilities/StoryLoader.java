package com.jl426551.example.jlfnews.GuardianUtilities;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.jl426551.example.jlfnews.BingUtilities.News;

import java.util.ArrayList;

public class StoryLoader extends AsyncTaskLoader<ArrayList<News>> {


    String query;

    public StoryLoader(Context context, String search) {
        super(context);

        //Formats complete Guardian API url.
        query = search;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        return StorySearchClass.search(query);
    }


}