package com.ondrejruttkay.architecturedemo.viewmodel;

import android.databinding.ObservableField;

import com.ondrejruttkay.architecturedemo.common.databinding.Command;
import com.ondrejruttkay.architecturedemo.common.di.PerActivity;
import com.ondrejruttkay.architecturedemo.common.event.LanguageChanged;
import com.ondrejruttkay.architecturedemo.common.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.ondrejruttkay.architecturedemo.common.navigation.INavigator;
import com.ondrejruttkay.architecturedemo.common.repository.IRepository;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Created by Onko on 08/09/2016.
 */
@PerActivity
public class EditPostViewModel extends BaseViewModel {

    private Post post;
    private INavigator navigator;
    private IRepository repository;
    private ILocalization localization;

    private Command saveCommand;

    private ObservableField<String> title;
    private ObservableField<String> summary;

    @Inject
    public EditPostViewModel(Bus bus,
                             INavigator navigator,
                             IRepository repository,
                             ILocalization localization) {
        super(bus);

        this.navigator = navigator;
        this.repository = repository;
        this.localization = localization;

        this.saveCommand = new Command(this::savePost);

        this.title = new ObservableField<>();
        this.summary = new ObservableField<>();
    }

    public void loadPost(int id) {
        this.post = repository.getPost(id);

        if (post != null) {
            this.title.set(post.getTitle());
            this.summary.set(post.getSummary());
        }
    }

    private void savePost() {
        if (post != null) {
            post.update(title.get(), summary.get());
        }
        navigator.close();
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public ObservableField<String> getSummary() {
        return summary;
    }

    public Command getSaveCommand() {
        return saveCommand;
    }

    public ILocalization getLocalization() {
        return localization;
    }

    @Subscribe
    public void onLanguageChanged(LanguageChanged event) {
        notifyChange();
    }
}
