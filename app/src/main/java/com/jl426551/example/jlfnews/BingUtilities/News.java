package com.jl426551.example.jlfnews.BingUtilities;

public class News {

    private String title;
    private String articleURL;
    private String imageURL;
    private String source;

    public News(String title, String url, String source, String imageURL) {
        this.title = title;
        this.articleURL = url;
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

    public String getSummary() {
        return "" + title.substring(0, 4) + " at " + source;
    }
}
