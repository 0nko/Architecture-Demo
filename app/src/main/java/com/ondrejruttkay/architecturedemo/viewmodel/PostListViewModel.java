package com.ondrejruttkay.architecturedemo.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.ondrejruttkay.architecturedemo.databinding.Command;
import com.ondrejruttkay.architecturedemo.di.PerActivity;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.ondrejruttkay.architecturedemo.navigation.INavigator;
import com.ondrejruttkay.architecturedemo.network.IRestApiClient;
import com.ondrejruttkay.architecturedemo.rx.RxUtils;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by Onko on 08/09/2016.
 */
@PerActivity
public class PostListViewModel extends BaseViewModel {

    private IRestApiClient apiClient;
    private INavigator navigator;

    private ObservableList<PostViewModel> posts;
    private ObservableBoolean isBusy;
    private Command loadCommand;

    @Inject
    public PostListViewModel(Bus bus, IRestApiClient apiClient, INavigator navigator) {
        super(bus);

        this.apiClient = apiClient;
        this.navigator = navigator;

        this.posts = new ObservableArrayList<>();
        this.isBusy = new ObservableBoolean();
        this.loadCommand = new Command(this::loadPosts);
    }

    @Override
    public void onPropertyChanged(Observable observable) {
        if (observable == posts) {
            loadCommand.setVisible(posts.size() == 0);
        }
    }

    private void loadPosts() {
        isBusy.set(true);
        loadCommand.setVisible(false);

        apiClient.requestPosts()
                .compose(RxUtils.applySchedulers())
                .compose(RxUtils.applyDelay())
                .subscribe(newPosts -> {
                    posts.clear();
                    for (Post post : newPosts) {
                        posts.add(new PostViewModel(getBus(), post, navigator));
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
}
