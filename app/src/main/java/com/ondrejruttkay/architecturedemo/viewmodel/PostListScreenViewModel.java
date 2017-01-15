package com.ondrejruttkay.architecturedemo.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.ondrejruttkay.architecturedemo.common.databinding.Command;
import com.ondrejruttkay.architecturedemo.common.di.PerActivity;
import com.ondrejruttkay.architecturedemo.common.event.LanguageChanged;
import com.ondrejruttkay.architecturedemo.common.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.common.navigation.INavigator;
import com.ondrejruttkay.architecturedemo.common.repository.IRepository;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Onko on 08/09/2016.
 */
@PerActivity
public class PostListScreenViewModel extends ScreenViewModel {

    private ILocalization localization;

    @Inject
    public PostListScreenViewModel(Bus bus, ILocalization localization) {
        super(bus);

        this.localization = localization;
    }

    public void toggleLanguage() {
        localization.toggleLanguage();
    }
}
