package com.example.alfred.recycleview;


public class Article {
    private String mTitle;
    private String mContent;



    public Article(String title, String content) {

        mTitle = title;
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }
}
