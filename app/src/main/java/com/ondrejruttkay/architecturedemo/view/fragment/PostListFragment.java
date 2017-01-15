package com.ondrejruttkay.architecturedemo.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.databinding.FragmentPostsBinding;
import com.ondrejruttkay.architecturedemo.viewmodel.PostListComponentViewModel;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class PostListFragment extends BaseFragment<PostListComponentViewModel> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentPostsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_posts,
                container,
                false);

        binding.setViewModel(getViewModel());

        return binding.getRoot();
    }

    @Override
    public void injectFragment() {
        getInjection().inject(this);
    }
}
