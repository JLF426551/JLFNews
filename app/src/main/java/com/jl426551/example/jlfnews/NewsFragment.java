package com.jl426551.example.jlfnews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jl426551.example.jlfnews.DataUtilities.News;
import com.jl426551.example.jlfnews.DataUtilities.NewsAdapter;
import com.jl426551.example.jlfnews.DataUtilities.CategoryUtilities;
import com.jl426551.example.jlfnews.DataUtilities.NewsLoader;

import java.util.ArrayList;

public class NewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<News>> {

    NewsAdapter adapter;
    LinearLayoutManager manager;
    ArrayList<News> newsList;
    final int LOADER_ID = 23;
    RecyclerView rv;

    int topic = -1;
    int searchEngine = -1;


    public NewsFragment() {
        Log.v("NFragment", "created");

    }

    public void addIndex(int selection) {
        topic = selection;
    }

    public void addEngine(int searchEngineSelection)
    {
        Log.v("NFragment", "engine " + searchEngineSelection);
        searchEngine = searchEngineSelection;
    }

    private void setAdapter() {
        adapter = new NewsAdapter(newsList);
        manager = new LinearLayoutManager(getContext());

        //Calculate which position to scroll.
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_fragment_layout, container, false);

        rv = rootView.findViewById(R.id.news_rv);

        getLoaderManager().restartLoader(LOADER_ID, null, this).forceLoad();

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, @Nullable Bundle args) {

        Log.v("NF", "onCreate loader");
        Log.v("NF", "enginge " + searchEngine);
        return new NewsLoader(getContext(), searchEngine, CategoryUtilities.getTitle(getContext(), topic));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<News>> loader, ArrayList<News> data) {

        newsList = data;
        setAdapter();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<News>> loader) {

    }

}
