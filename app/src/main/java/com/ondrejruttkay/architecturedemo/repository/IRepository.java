package com.ondrejruttkay.architecturedemo.repository;

import com.ondrejruttkay.architecturedemo.model.Post;

import java.util.List;

import rx.Observable;

/**
 * Requests posts from some API
 */
public interface IRepository {

    Observable<List<Post>> requestPosts();
    Post getPost(int id);
}
