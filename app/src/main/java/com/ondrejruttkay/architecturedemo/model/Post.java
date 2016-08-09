package com.ondrejruttkay.architecturedemo.model;

import android.support.annotation.Nullable;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class Post {
    String title;
    String summary;
    String url;
    String likes;
    String comments;
    String imageUrl;

    public Post() {}

    public Post(String tile, String summary, String url, String likes, String comments, @Nullable String imageUrl) {
        this.title = tile;
        this.summary = summary;
        this.url = url;
        this.likes = likes;
        this.comments = comments;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getUrl() {
        return url;
    }

    public String getLikes() {
        return likes;
    }

    public String getComments() {
        return comments;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }
}