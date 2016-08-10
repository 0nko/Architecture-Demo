package com.ondrejruttkay.architecturedemo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_language:
                viewModel.toggleLanguage();
                break;
        }
        return true;
    }
}