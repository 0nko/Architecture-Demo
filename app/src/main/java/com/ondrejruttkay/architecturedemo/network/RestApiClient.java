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
        posts.add(new Post("Data Binding Rulez", "Having the views and business logic (BL) mixed up in 5000 " +
                "line-long activities is nice and all but it doesn’t have to be that way. One of the key " +
                "advantages of using data binding is a chance to nicely separate the presentation layer from the BL.",
                "https://hogwartsp2.wordpress.com/2016/07/28/the-magic-of-android-data-binding-library/",
                "5", "4", "https://i2.wp.com/cases.azoft.com/images/2015/12/pattern-mvvm-scheme.png"));
        posts.add(new Post("Saturday renamed Caturday", "Lolcats take over the planet.",
                "https://hogwartsp2.wordpress.com/2016/08/09/hackday-fluxc-rest-improvements/",
                "1", "14", null));

        return Observable.just(posts);
    }
}
