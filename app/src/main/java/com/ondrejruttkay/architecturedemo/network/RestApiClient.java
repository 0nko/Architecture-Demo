package com.ondrejruttkay.architecturedemo.network;

import com.ondrejruttkay.architecturedemo.model.Post;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Onko on 08/09/2016.
 */
public class RestApiClient implements IRestApiClient {

    @Override
    public Observable<List<Post>> requestPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Data Binding", "This is a summary",
                "https://hogwartsp2.wordpress.com/2016/07/28/the-magic-of-android-data-binding-library/",
                "5", "4", "https://i2.wp.com/cases.azoft.com/images/2015/12/pattern-mvvm-scheme.png"));
        posts.add(new Post("Trump elected", "This is a summary 2",
                "https://hogwartsp2.wordpress.com/2016/08/09/hackday-fluxc-rest-improvements/",
                "1", "14", null));

        return Observable.just(posts);
    }
}
