package com.jl426551.example.jlfnews.DataUtilities;

import android.content.Context;

import com.jl426551.example.jlfnews.R;

import java.util.ArrayList;

public class CategoryUtilities {

    private final static int DO_NOT_INCLUDE = 0;

    private final static int CATEGORY_TOP = 0;
    private final static int CATEGORY_POLITICS = 1;
    private final static int CATEGORY_BUSINESS = 2;
    private final static int CATEGORY_US = 3;
    private final static int CATEGORY_WORLD = 4;
    private final static int CATEGORY_ENTERTAINMENT = 5;
    private final static int CATEGORY_SCIENCE = 6;
    private final static int CATEGORY_TECHNOLOGY = 7;
    private final static int CATEGORY_SPORTS = 8;

    public static ArrayList<String> getDefaultTabTitle(Context context) {
        ArrayList<String> titles = new ArrayList<>();
        titles.add(context.getString(R.string.news_top));
        titles.add(context.getString(R.string.news_politics));
        titles.add(context.getString(R.string.news_business));
        titles.add(context.getString(R.string.news_us));
        titles.add(context.getString(R.string.news_world));
        titles.add(context.getString(R.string.news_entertainment));
        titles.add(context.getString(R.string.news_science));
        titles.add(context.getString(R.string.news_technology));
        titles.add(context.getString(R.string.news_sports));

        return titles;
    }

    public static ArrayList<Integer> getIndeces() {
        ArrayList<Integer> indecesList = new ArrayList<>();
        //indecesList.add(0);
        //indecesList.add(1);
        //indecesList.add(2);
        indecesList.add(CATEGORY_US);
        indecesList.add(CATEGORY_WORLD);
        indecesList.add(CATEGORY_ENTERTAINMENT);
        indecesList.add(CATEGORY_SCIENCE);
        indecesList.add(CATEGORY_TECHNOLOGY);
        indecesList.add(CATEGORY_SPORTS);

        return indecesList;
    }

    //Methods returns an ArrayList of String which correspond to the tab
    //titles as selected by the user.
    public static ArrayList<String> getSelectedTabs(Context context, int[] selection) {
        //Build full array list.
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Top News");
        titles.add("Politics");
        titles.add("Business");
        titles.add("U.S.");
        titles.add("World");
        titles.add("Entertainment");
        titles.add("Science");
        titles.add("Technology");
        titles.add("Sports");

        //Remove values which are not wanted by user.
        for (int k = selection.length - 1; k >= 0; k--) {
            if (selection[k] == DO_NOT_INCLUDE)
                titles.remove(k);
        }

        return titles;
    }

    public static String getTitle(Context context, int categoryIndex) {

        String title = "";

        switch (categoryIndex) {
            case CATEGORY_POLITICS:
                title = context.getString(R.string.news_politics);
                break;
            case CATEGORY_BUSINESS:
                title = context.getString(R.string.news_business);
                break;
            case CATEGORY_US:
                title = context.getString(R.string.news_us);
                break;
            case CATEGORY_WORLD:
                title = context.getString(R.string.news_world);
                break;
            case CATEGORY_ENTERTAINMENT:
                title = context.getString(R.string.news_entertainment);
                break;
            case CATEGORY_SCIENCE:
                title = context.getString(R.string.news_science);
                break;
            case CATEGORY_TECHNOLOGY:
                title = context.getString(R.string.news_technology);
                break;
            case CATEGORY_SPORTS:
                title = context.getString(R.string.news_sports);
                break;
            case CATEGORY_TOP:
                title = context.getString(R.string.news_top);
            default:

        }

        return title;
    }
}
