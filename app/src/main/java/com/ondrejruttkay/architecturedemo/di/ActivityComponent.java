package com.ondrejruttkay.architecturedemo.di;


import com.ondrejruttkay.architecturedemo.activity.EditPostActivity;
import com.ondrejruttkay.architecturedemo.activity.PostListActivity;
import com.ondrejruttkay.architecturedemo.fragment.EditPostFragment;
import com.ondrejruttkay.architecturedemo.fragment.PostListFragment;
import com.ondrejruttkay.architecturedemo.fragment.StateFragment;
import com.ondrejruttkay.architecturedemo.viewmodel.EditPostViewModel;
import com.ondrejruttkay.architecturedemo.viewmodel.PostListViewModel;
import com.ondrejruttkay.architecturedemo.viewmodel.PostViewModel;

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
