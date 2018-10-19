package com.jl426551.example.jlfnews.SearchClasses;

import android.util.Log;

import com.jl426551.example.jlfnews.DataUtilities.NetworkUtilities;
import com.jl426551.example.jlfnews.DataUtilities.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class StorySearchClass {

    private final static String preFix = "https://content.guardianapis.com/search?q=";
    private final static String postFix = "&from-date=2017-01-01&api-key=test";

    public static ArrayList<News> search(String query) {

        String searchQuery = preFix + query + postFix;
        ArrayList<News> returnList = new ArrayList<>();
        String networkResponse;

        try {
            networkResponse = NetworkUtilities.makeHttpRequest(searchQuery);
            returnList = createBookList(networkResponse);
        } catch (IOException e) {
            Log.e("Loader", "Background network call", e);
        }

        return returnList;

    }

    public static ArrayList<News> createBookList(String jsonresponse) {
         /* Used as the algorithm iterates through each object. They're used to create the
        individual Book objects added to the list. */
        String tempTitle;
        String tempDate;
        String tempSection;
        String tempURL;

        //The list which will be populated and returned.
        ArrayList<News> list = new ArrayList<>();

        try {
            JSONObject firstLevelObject = new JSONObject(jsonresponse);
            JSONObject response = firstLevelObject.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            int length = results.length();

            JSONObject tempObject;

            //Iterates the results JSONArray and creates the Story objects added to 'list.'
            for (int i = 0; i < length; i++) {
                tempObject = results.getJSONObject(i);

                tempTitle = tempObject.getString("webTitle");

                //Obtains publication time and formats it to not show hour and minute; only date.
                tempDate = tempObject.getString("webPublicationDate");
                tempDate = tempDate.substring(0, 10);

                tempURL = tempObject.getString("webUrl");

                list.add(new News(tempTitle, tempURL, tempDate, "Guardian", null));
            }

        } catch (JSONException e) {
            Log.e("Unable to parse JSON", e.toString());
        }

        return list;
    }
}