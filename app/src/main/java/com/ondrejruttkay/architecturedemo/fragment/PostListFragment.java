package com.ondrejruttkay.architecturedemo.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.activity.BaseActivity;
import com.ondrejruttkay.architecturedemo.databinding.FragmentPostsBinding;
import com.ondrejruttkay.architecturedemo.viewmodel.PostListViewModel;

import javax.inject.Inject;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class PostListFragment extends BaseFragment {

    @Inject
    PostListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((BaseActivity)getActivity()).getStateFragment().getInjection().inject(this);

        FragmentPostsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_posts,
                container,
                false);

        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}
