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
import com.ondrejruttkay.architecturedemo.common.util.RxUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Onko on 08/09/2016.
 */
@PerActivity
public class PostListViewModel extends BaseViewModel {

    private IRepository repository;
    private INavigator navigator;
    private ILocalization localization;

    private ObservableList<PostViewModel> posts;
    private ObservableBoolean isBusy;
    private Command loadCommand;

    @Inject
    public PostListViewModel(Bus bus, IRepository repository, INavigator navigator, ILocalization localization) {
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

        repository.requestPosts()
                .compose(RxUtils.applyDelay())
                .compose(RxUtils.applySchedulers())
                .subscribe(this::onPostsLoaded, throwable -> isBusy.set(false));
    }

    public void toggleLanguage() {
        localization.toggleLanguage();
    }

    public void deletePost(@NonNull PostViewModel post) {
        posts.remove(post);
        post.onDestroy();

        loadCommand.setVisible(posts.size() == 0);
    }

    //region Getters

    public ObservableList<PostViewModel> getPosts() {
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
            PostViewModel newPost = new PostViewModel(getBus(), post, navigator, localization, this);
            newPost.onCreate();
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

        for (PostViewModel post : posts) {
            post.onDestroy();
        }
    }

    //endregion
}
