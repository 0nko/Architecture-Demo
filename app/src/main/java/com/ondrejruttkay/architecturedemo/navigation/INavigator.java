package com.ondrejruttkay.architecturedemo.navigation;

import com.ondrejruttkay.architecturedemo.model.Post;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public interface INavigator {
    void showMessage(String message);
    void close();
    void openUrl(String url);
    void editPost(Post post);
}
