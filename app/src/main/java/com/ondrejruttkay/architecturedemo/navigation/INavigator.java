package com.ondrejruttkay.architecturedemo.navigation;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public interface INavigator {
    void close();
    void showMainScreen();
    void openUrl(String url);
}
