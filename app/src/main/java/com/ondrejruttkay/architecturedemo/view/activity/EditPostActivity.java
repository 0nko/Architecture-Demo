package com.ondrejruttkay.architecturedemo.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.databinding.ActivityEditPostBinding;
import com.ondrejruttkay.architecturedemo.viewmodel.ScreenViewModel;

/**
 * Created by Onko on 8/10/2016.
 */
public class EditPostActivity extends BaseActivity<ScreenViewModel> {

    ActivityEditPostBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_post);
        setSupportActionBar(binding.toolbarOverlay.toolbar);
        setupToolbarWithBackButton();
    }

    @Override
    public void injectStateFragment() {
        getInjection().injectScreenState(getStateFragment());
    }

    @Override
    protected void injectActivity() {
        getInjection().inject(this);
    }
}
