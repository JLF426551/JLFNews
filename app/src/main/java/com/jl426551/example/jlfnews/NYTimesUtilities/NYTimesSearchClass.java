package com.jl426551.example.jlfnews.NYTimesUtilities;

import android.util.Log;

import com.jl426551.example.jlfnews.BingUtilities.News;
import com.jl426551.example.jlfnews.GuardianUtilities.NetworkUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class NYTimesSearchClass {

    final static String API_KEY = "";

    static String preFix = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=";
    static String postFix = "&f1=headline,web_url,source,multimedia&api-key=";
    final static int LIMIT = 20;

    public static ArrayList<News> search(String query) {

        ArrayList<News> list = new ArrayList<>();
        String completeURL = preFix + query + postFix + API_KEY;
        String networkResponse;
        try {
            networkResponse = NetworkUtilities.makeHttpRequest(completeURL);
            list = createList(networkResponse);
        } catch (IOException e) {
            Log.e("NYTimes Search", e.toString());
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
            JSONObject response = firstLevelObject.getJSONObject("response");
            JSONArray docs = response.getJSONArray("docs");
            int length = docs.length();

            JSONObject tempObject;
            JSONArray multimediaArray;

            //Iterates the results JSONArray and creates the News objects added to 'list.'
            if (length > LIMIT)
                length = LIMIT;

            for (int i = 0; i < length; i++) {
                tempObject = docs.getJSONObject(i);

                //Skips over any entries that aren't articles. e.g. Topics
                if (tempObject.has("source")) {
                    tempSource = tempObject.getString("source");
                    tempURL = tempObject.getString("web_url");
                    tempTitle = tempObject.getJSONObject("headline").getString("main");
                    tempDate = tempObject.getString("pub_date").substring(0, 10);

                    multimediaArray = tempObject.getJSONArray("multimedia");

                    //Only proceed if the array has any elements.
                    if (multimediaArray.length() > 0) {
                        tempImageURL = "http://nytimes.com/" + multimediaArray.getJSONObject(1).getString("url");
                    } else
                        tempImageURL = null;

                    list.add(new News(tempTitle, tempURL, tempDate, tempSource, tempImageURL));
                }
            }

        } catch (JSONException e) {
            Log.v("Unable to parse JSON", e.toString());
        }

        return list;
    }
}
