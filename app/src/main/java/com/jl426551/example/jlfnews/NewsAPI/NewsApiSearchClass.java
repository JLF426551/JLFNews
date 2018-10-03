package com.jl426551.example.jlfnews.NewsAPI;

import android.util.Log;

import com.jl426551.example.jlfnews.BingUtilities.News;
import com.jl426551.example.jlfnews.GuardianUtilities.NetworkUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class NewsApiSearchClass {

    private final static String API_KEY = "";
    private final static String preFix = "https://newsapi.org/v2/everything?q=";
    private final static String keyHolder = "&apiKey=" + API_KEY;

    public NewsApiSearchClass() {

    }

    public static ArrayList<News> search(String query) {
        String fullUrl = preFix + query + keyHolder;
        ArrayList<News> list = new ArrayList<>();

        String networkResponse;
        try {
            networkResponse = NetworkUtilities.makeHttpRequest(fullUrl);
            list = createList(networkResponse);
        } catch (IOException e) {
            Log.e("API Search", e.toString());
        }
        return list;
    }

    private static ArrayList<News> createList(String jsonresponse) {
         /* Used as the algorithm iterates through each object. They're used to create the
        individual Book objects added to the list. */
        String tempTitle;
        String tempURL;
        String tempDate;
        String tempSource;
        String tempImageURL;

        //The list which will be populated and returned.
        ArrayList<News> list = new ArrayList<>();

        try {
            JSONObject firstLevelObject = new JSONObject(jsonresponse);
            JSONArray results = firstLevelObject.getJSONArray("articles");
            int length = results.length();

            JSONObject tempObject;
            JSONObject tempSourceObject;
            //Iterates the results JSONArray and creates the Story objects added to 'list.'
            for (int i = 0; i < length; i++) {
                tempObject = results.getJSONObject(i);

                tempSource = tempObject.getJSONObject("source").getString("name");
                tempTitle = tempObject.getString("title");
                tempURL = tempObject.getString("url");
                tempImageURL = tempObject.getString("urlToImage");
                tempDate = tempObject.getString("publishedAt").substring(0,10);

                list.add(new News(tempTitle, tempURL,tempDate, tempSource, tempImageURL));
            }

        } catch (JSONException e) {
            Log.v("Unable to parse JSON", e.toString());
        }

        return list;
    }
}