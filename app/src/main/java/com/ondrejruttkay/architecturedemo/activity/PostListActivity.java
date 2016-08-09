package com.ondrejruttkay.architecturedemo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.databinding.ActivityPostsBinding;
import com.ondrejruttkay.architecturedemo.viewmodel.PostListViewModel;

/**
 * The main activity with a toolbar.
 */
public class PostListActivity extends BaseActivity<PostListViewModel> {

    ActivityPostsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts);
        setSupportActionBar(binding.toolbarOverlay.toolbar);
    }

    @Override
    public void injectStateFragment() {
        getStateFragment().getInjection().injectPostListState(getStateFragment());
    }

    @Override
    protected void injectActivity() {
        getStateFragment().getInjection().inject(this);
    }
}