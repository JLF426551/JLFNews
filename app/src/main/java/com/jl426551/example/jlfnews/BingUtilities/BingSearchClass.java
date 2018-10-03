package com.jl426551.example.jlfnews.BingUtilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class BingSearchClass {

    // Enter a valid subscription key.
    static String subscriptionKey = "326b343d1aeb47119a6786f5c78d1484";

    /*
     * If you encounter unexpected authorization errors, double-check these values
     * against the endpoint for your Bing Web search instance in your Azure
     * dashboard.
     */
    static String HOST = "https://api.cognitive.microsoft.com";
    final static String PATH = "/bing/v7.0/news/search";
    final static String countLimit = "&count=25";

    public static ArrayList<News> search(String query) {

        String searchTerm = query;
        String result = "";

        // Confirm the subscriptionKey is valid.
        if (subscriptionKey.length() != 32) {
            System.out.println("Invalid Bing Search API subscription key!");
            System.out.println("Please paste yours into the source code.");
        }

        // Call the SearchWeb method and print the response.
        try {
            result = SearchWeb(searchTerm);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return parseNewsObject(result, searchTerm);
    }

    public static String SearchWeb(String searchQuery) throws Exception {

        URL url = null;

        // Construct the URL.
        try {
            url = new URL(HOST + PATH + "?q=" + URLEncoder.encode(searchQuery, "UTF-8") + countLimit);
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            Log.e("BSC Error", e.toString());
        }

        if (url == null)
            return "unable to search";

        InputStream inputStream = null;
        HttpsURLConnection urlConnection = null;
        InputStream stream;
        String response = "";

        try {
            // Open the connection.
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Receive the JSON response body.
            stream = urlConnection.getInputStream();
            response = new Scanner(stream).useDelimiter("\\A").next();
        } catch (IOException e) {
            Log.e("BSC", "Problem retrieving JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return response;
    }

    private static ArrayList<News> parseNewsObject(String jsonresult, String searchTerm) {

        ArrayList<News> newsList = new ArrayList<>();
        JSONArray newsArray;
        int length;

        JSONObject defaultJSONObject;
        JSONObject tempObject;

        String tempTitle;
        String tempDate;
        String tempArticleURL;
        String tempImageURL;
        String tempSource;

        try {
            defaultJSONObject = new JSONObject(jsonresult);

            //Initialize the array under 'value'
            newsArray = defaultJSONObject.getJSONArray("value");
            length = newsArray.length();

            //Limit stories displayed.
            if (length > 25)
                length = 25;

            //Iterates the results JSONArray and creates the Story objects added to 'list.'
            for (int i = 0; i < length; i++) {
                tempObject = newsArray.getJSONObject(i);

                tempTitle = tempObject.getString("name");
                tempArticleURL = tempObject.getString("url");
                tempDate = tempObject.getString("datePublished").substring(0, 10);
                JSONArray subLevel = tempObject.getJSONArray("provider");
                JSONObject providerObject = subLevel.getJSONObject(0);
                tempSource = providerObject.getString("name");
                tempImageURL = providerObject.getJSONObject("image").getJSONObject("thumbnail").getString("contentUrl");

                newsList.add(new News(tempTitle, tempArticleURL, tempDate, tempSource, tempImageURL));
            }
        } catch (JSONException e) {
            Log.e("BingSearchClass e", e.toString());
            return newsList;
        }
        return newsList;
    }

}

