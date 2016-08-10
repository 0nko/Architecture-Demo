package com.ondrejruttkay.architecturedemo.common.di;


import com.ondrejruttkay.architecturedemo.view.activity.EditPostActivity;
import com.ondrejruttkay.architecturedemo.view.activity.PostListActivity;
import com.ondrejruttkay.architecturedemo.view.fragment.EditPostFragment;
import com.ondrejruttkay.architecturedemo.view.fragment.PostListFragment;
import com.ondrejruttkay.architecturedemo.view.fragment.StateFragment;
import com.ondrejruttkay.architecturedemo.viewmodel.EditPostViewModel;
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
    void injectEditPostState(StateFragment<EditPostViewModel> fragment);

    // Activities
    void inject(PostListActivity activity);
    void inject(EditPostActivity activity);

    // Fragments
    void inject(PostListFragment fragment);
    void inject(EditPostFragment fragment);
}
