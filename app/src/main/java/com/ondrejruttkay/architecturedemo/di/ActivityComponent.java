package com.ondrejruttkay.architecturedemo.di;


import com.ondrejruttkay.architecturedemo.activity.PostListActivity;
import com.ondrejruttkay.architecturedemo.fragment.PostListFragment;
import com.ondrejruttkay.architecturedemo.fragment.StateFragment;
import com.ondrejruttkay.architecturedemo.viewmodel.PostListViewModel;

import dagger.Subcomponent;

/**
 * Dagger component for dependency injection.
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    // State fragments
    void injectPostListState(StateFragment<PostListViewModel> fragment);

    // Activities
    void inject(PostListActivity activity);

    // Fragments
    void inject(PostListFragment fragment);
}
