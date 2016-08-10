package com.ondrejruttkay.architecturedemo.common.event;

/**
 * Created by Onko on 8/11/2016.
 */
public class PostChanged {

    private int id;

    public PostChanged(int id) {
        this.id = id;
    }

    public int getPostId() {
        return id;
    }
}
