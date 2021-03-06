package com.ondrejruttkay.architecturedemo.viewmodel;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.ondrejruttkay.architecturedemo.common.databinding.Command;
import com.ondrejruttkay.architecturedemo.common.di.PerActivity;
import com.ondrejruttkay.architecturedemo.common.event.LanguageChanged;
import com.ondrejruttkay.architecturedemo.common.event.PostChanged;
import com.ondrejruttkay.architecturedemo.common.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.ondrejruttkay.architecturedemo.common.navigation.INavigator;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Created by Onko on 08/09/2016.
 */
public class PostComponentViewModel extends ComponentViewModel {

    private Post post;
    private INavigator navigator;
    private ILocalization localization;
    private PostListComponentViewModel postListViewModel;

    private Command openCommand;
    private Command editCommand;
    private Command deleteCommand;

    public PostComponentViewModel(Bus bus,
                                  Post post,
                                  INavigator navigator,
                                  ILocalization localization,
                                  PostListComponentViewModel postListViewModel) {
        super(bus);

        this.post = post;

        this.navigator = navigator;
        this.localization = localization;
        this.postListViewModel = postListViewModel;

        this.openCommand = new Command(this::openUrl);
        this.editCommand = new Command(this::editPost);
        this.deleteCommand = new Command(this::deletePost);
    }

    private void openUrl() {
        navigator.openUrl(post.getUrl());
    }

    private void editPost() {
        navigator.editPost(post);
    }

    private void deletePost() {
        postListViewModel.deletePost(this);
    }

    //region Getters

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

    public Command getOpenCommand() {
        return openCommand;
    }

    public Command getEditCommand() {
        return editCommand;
    }

    public Command getDeleteCommand() {
        return deleteCommand;
    }

    public ILocalization getLocalization() {
        return localization;
    }

    //endregion

    //region Events

    @Subscribe
    public void onLanguageChanged(LanguageChanged event) {
        notifyChange();
    }

    @Subscribe
    public void onPostChanged(PostChanged event) {
        if (event.getPostId() == post.getId()) {
            notifyChange();
        }
    }

    //endregion
}
