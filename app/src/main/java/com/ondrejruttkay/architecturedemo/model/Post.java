package com.ondrejruttkay.architecturedemo.model;

import android.support.annotation.Nullable;

import com.ondrejruttkay.architecturedemo.common.event.PostChanged;
import com.squareup.otto.Bus;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class Post {
    private Bus bus;
    private int id;
    private String title;
    private String summary;
    private String url;
    private String likes;
    private String comments;
    private String imageUrl;

    public Post(Bus bus,
                int id,
                String tile,
                String summary,
                String url,
                String likes,
                String comments,
                @Nullable String imageUrl) {

        this.bus = bus;
        this.id = id;
        this.title = tile;
        this.summary = summary;
        this.url = url;
        this.likes = likes;
        this.comments = comments;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
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

    public void update(String title, String summary) {
        this.title = title;
        this.summary = summary;

        bus.post(new PostChanged(id));
    }
}