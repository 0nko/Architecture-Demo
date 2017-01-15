package com.ondrejruttkay.architecturedemo.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ondrejruttkay.architecturedemo.DemoConfig;
import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.databinding.FragmentEditPostBinding;
import com.ondrejruttkay.architecturedemo.viewmodel.EditPostComponentViewModel;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class EditPostFragment extends BaseFragment<EditPostComponentViewModel> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentEditPostBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_edit_post,
                container,
                false);

        binding.setViewModel(getViewModel());

        if (getActivity().getIntent().hasExtra(DemoConfig.EDIT_POST_KEY)) {
            getViewModel().loadPost(getActivity().getIntent().getIntExtra(DemoConfig.EDIT_POST_KEY, -1));
        }

        return binding.getRoot();
    }

    @Override
    public void injectFragment() {
        getInjection().inject(this);
    }
}
