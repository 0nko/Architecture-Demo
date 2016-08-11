package com.ondrejruttkay.architecturedemo.common.repository;

import com.ondrejruttkay.architecturedemo.common.util.RxUtils;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Onko on 8/11/2016.
 */

public class FakeRepository extends Repository {
    public FakeRepository(Bus bus) {
        super(bus);
    }

    @Override
    public Observable<List<Post>> requestPosts() {
        return Observable.just(new ArrayList<>(posts.values()))
                .compose(RxUtils.applyDelay());
    }
}
