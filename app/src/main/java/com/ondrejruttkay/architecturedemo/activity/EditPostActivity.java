package com.ondrejruttkay.architecturedemo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ondrejruttkay.architecturedemo.DemoConfig;
import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.databinding.ActivityEditPostBinding;
import com.ondrejruttkay.architecturedemo.viewmodel.EditPostViewModel;

/**
 * Created by Onko on 8/10/2016.
 */
public class EditPostActivity extends BaseActivity<EditPostViewModel> {
    ActivityEditPostBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_post);
        setSupportActionBar(binding.toolbarOverlay.toolbar);
        setupToolbarWithBackButton();

        if (getIntent().hasExtra(DemoConfig.EDIT_POST_KEY)) {
            viewModel.loadPost(getIntent().getIntExtra(DemoConfig.EDIT_POST_KEY, -1));
        }
    }

    @Override
    public void injectStateFragment() {
        getStateFragment().getInjection().injectEditPostState(getStateFragment());
    }

    @Override
    protected void injectActivity() {
        getStateFragment().getInjection().inject(this);
    }
}
