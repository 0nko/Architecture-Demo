package com.ondrejruttkay.architecturedemo.viewmodel;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.ondrejruttkay.architecturedemo.databinding.Command;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.ondrejruttkay.architecturedemo.navigation.INavigator;
import com.squareup.otto.Bus;

/**
 * Created by Onko on 08/09/2016.
 */
public class PostViewModel extends BaseViewModel {

    private Post post;
    private INavigator navigator;

    private Command openCommand;
    private Command editCommand;

    public PostViewModel(Bus bus, Post post, INavigator navigator) {
        super(bus);

        this.post = post;
        this.navigator = navigator;

        this.openCommand = new Command(this::openUrl);
        this.editCommand = new Command(this::editPost);
    }

    private void openUrl() {
        navigator.openUrl(post.getUrl());
    }

    private void editPost() {
    }

    public String getTitle() {
        return post.getTitle();
    }

    public String getSummary() {
        return post.getSummary();
    }

    public String getLikes() {
        return post.getLikes();
    }

    public String getComments() {
        return post.getComments();
    }

    @Nullable
    public Uri getImageUrl() {
        return post.getImageUrl() != null ? Uri.parse(post.getImageUrl()) : null;
    }

    public void refresh() {
        notifyChange();
    }

    public Command getOpenCommand() {
        return openCommand;
    }

    public Command getEditCommand() {
        return editCommand;
    }
}
