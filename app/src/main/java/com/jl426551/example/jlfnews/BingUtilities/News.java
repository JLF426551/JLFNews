package com.jl426551.example.jlfnews.BingUtilities;

public class News {

    private String title;
    private String articleURL;
    private String imageURL;
    private String source;
    private String date;

    public News(String title, String url, String date, String source, String imageURL) {
        this.title = title;
        this.articleURL = url;
        this.date = date;
        this.source = source;
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public String getURL() {
        return articleURL;
    }

    public String getSource() {
        return source;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDate()
    {
        return date;
    }

}
