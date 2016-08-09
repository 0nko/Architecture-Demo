package com.ondrejruttkay.architecturedemo.network;

import com.ondrejruttkay.architecturedemo.model.Post;

import java.util.List;

import rx.Observable;

/**
 * Requests posts from some API
 */
public interface IRestApiClient {

    Observable<List<Post>> requestPosts();
}
