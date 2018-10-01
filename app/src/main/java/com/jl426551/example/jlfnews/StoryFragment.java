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

import com.jl426551.example.jlfnews.DataUtilities.CategoryUtilities;
import com.jl426551.example.jlfnews.GuardianUtilities.Story;
import com.jl426551.example.jlfnews.GuardianUtilities.StoryAdapter;
import com.jl426551.example.jlfnews.GuardianUtilities.StoryLoader;

import java.util.ArrayList;

public class StoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Story>> {

    StoryAdapter adapter;
    LinearLayoutManager manager;
    ArrayList<Story> list;
    final int LOADER_ID = 23;
    RecyclerView rv;

    int topic = -1;


    public StoryFragment() {
    }

    public void addIndex(int selection) {
        topic = selection;
    }


    private void setAdapter() {
        adapter = new StoryAdapter(list);
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
    public Loader<ArrayList<Story>> onCreateLoader(int id, @Nullable Bundle args) {

        return new StoryLoader(getContext(), CategoryUtilities.getTitle(getContext(), topic));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Story>> loader, ArrayList<Story> data) {

        Log.v("NF", "on load finished");
        list = data;
        setAdapter();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Story>> loader) {

    }

}