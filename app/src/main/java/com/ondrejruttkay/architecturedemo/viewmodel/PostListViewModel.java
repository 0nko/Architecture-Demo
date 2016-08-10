package com.ondrejruttkay.architecturedemo.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.ondrejruttkay.architecturedemo.databinding.Command;
import com.ondrejruttkay.architecturedemo.di.PerActivity;
import com.ondrejruttkay.architecturedemo.event.LanguageChanged;
import com.ondrejruttkay.architecturedemo.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.ondrejruttkay.architecturedemo.navigation.INavigator;
import com.ondrejruttkay.architecturedemo.repository.IRepository;
import com.ondrejruttkay.architecturedemo.rx.RxUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Created by Onko on 08/09/2016.
 */
@PerActivity
public class PostListViewModel extends BaseViewModel {

    private IRepository apiClient;
    private INavigator navigator;
    private ILocalization localization;

    private ObservableList<PostViewModel> posts;
    private ObservableBoolean isBusy;
    private Command loadCommand;

    @Inject
    public PostListViewModel(Bus bus, IRepository apiClient, INavigator navigator, ILocalization localization) {
        super(bus);

        this.apiClient = apiClient;
        this.navigator = navigator;
        this.localization = localization;

        this.posts = new ObservableArrayList<>();

        this.isBusy = new ObservableBoolean();
        this.loadCommand = new Command(this::loadPosts);
    }

    private void loadPosts() {
        isBusy.set(true);
        loadCommand.setVisible(false);

        apiClient.requestPosts()
                .compose(RxUtils.applyDelay())
                .compose(RxUtils.applySchedulers())
                .subscribe(newPosts -> {
                    posts.clear();
                    for (Post post : newPosts) {
                        PostViewModel newPost = new PostViewModel(getBus(), post, navigator, localization, this);
                        newPost.onCreate();
                        posts.add(newPost);
                    }
                    isBusy.set(false);
                }, throwable -> isBusy.set(false));
    }

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

    public void toggleLanguage() {
        localization.toggleLanguage();
    }

    public void deletePost(PostViewModel post) {
        posts.remove(post);
        post.onDestroy();

        loadCommand.setVisible(posts.size() == 0);
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
}
