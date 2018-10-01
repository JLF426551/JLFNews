package com.jl426551.example.jlfnews.GuardianUtilities;

public class Story {

    String title;
    String section;
    String date;
    String url;


    public Story(String title, String section, String date, String url) {
        this.title = title;
        this.section = section;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }

    public String getURL() {
        return url;
    }
}
