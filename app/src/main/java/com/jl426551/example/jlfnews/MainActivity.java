package com.jl426551.example.jlfnews;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jl426551.example.jlfnews.DataUtilities.NewsLoader;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PageAdapter adapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.viewpager);

    }

    public void setUpAdapterViews(int searchEngineSelection) {

        adapter = null;

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new PageAdapter(this, getSupportFragmentManager(), searchEngineSelection);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.api_search_option:
                setUpAdapterViews(NewsLoader.API_NEWS_LOADER);
                break;
            case R.id.bing_search_option:
                setUpAdapterViews(NewsLoader.BING_LOADER);
                break;
            case R.id.guardian_search_option:
                setUpAdapterViews(NewsLoader.GUARDIAN_LOADER);
                break;
            case R.id.nyt_search_option:
                setUpAdapterViews(NewsLoader.NYT_LOADER);
        }

        return super.onOptionsItemSelected(item);
    }
}
