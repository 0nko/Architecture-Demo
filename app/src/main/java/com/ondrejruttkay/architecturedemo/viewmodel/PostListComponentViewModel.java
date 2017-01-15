package com.ondrejruttkay.architecturedemo.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.ondrejruttkay.architecturedemo.common.databinding.Command;
import com.ondrejruttkay.architecturedemo.common.di.PerActivity;
import com.ondrejruttkay.architecturedemo.common.event.LanguageChanged;
import com.ondrejruttkay.architecturedemo.common.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.ondrejruttkay.architecturedemo.common.navigation.INavigator;
import com.ondrejruttkay.architecturedemo.common.repository.IRepository;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Onko on 08/09/2016.
 */
@PerActivity
public class PostListComponentViewModel extends ComponentViewModel {

    private IRepository repository;
    private INavigator navigator;
    private ILocalization localization;

    private ObservableList<PostComponentViewModel> posts;
    private ObservableBoolean isBusy;
    private Command loadCommand;

    @Inject
    public PostListComponentViewModel(Bus bus, IRepository repository, INavigator navigator, ILocalization localization) {
        super(bus);

        this.repository = repository;
        this.navigator = navigator;
        this.localization = localization;

        this.posts = new ObservableArrayList<>();

        this.isBusy = new ObservableBoolean();
        this.loadCommand = new Command(this::loadPosts);
    }

    private void loadPosts() {
        isBusy.set(true);
        loadCommand.setVisible(false);

        repository.requestPosts().subscribe(this::onPostsLoaded, throwable -> isBusy.set(false));
    }

    public void toggleLanguage() {
        localization.toggleLanguage();
    }

    public void deletePost(@NonNull PostComponentViewModel post) {
        posts.remove(post);
        post.onDestroy();

        loadCommand.setVisible(posts.size() == 0);
    }

    //region Getters

    public ObservableList<PostComponentViewModel> getPosts() {
        return posts;
    }

    public Command getLoadCommand() {
        return loadCommand;
    }

    public ObservableBoolean getIsBusy() {
        return isBusy;
    }

    public ILocalization getLocalization() {
        return localization;
    }

    //endregion

    //region Events

    private void onPostsLoaded(List<Post> newPosts) {
        posts.clear();
        for (Post post : newPosts) {
            PostComponentViewModel newPost = new PostComponentViewModel(getBus(), post, navigator, localization, this);
            posts.add(newPost);
        }
        isBusy.set(false);
    }

    @Subscribe
    public void onLanguageChanged(LanguageChanged event) {
        notifyChange();
        navigator.showMessage(localization.getLanguageTitle());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        for (PostComponentViewModel post : posts) {
            post.onDestroy();
        }
    }

    //endregion
}
