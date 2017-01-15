package com.ondrejruttkay.architecturedemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ondrejruttkay.architecturedemo.DemoApp;
import com.ondrejruttkay.architecturedemo.common.di.ActivityComponent;
import com.ondrejruttkay.architecturedemo.view.activity.BaseActivity;
import com.ondrejruttkay.architecturedemo.viewmodel.ComponentViewModel;

import javax.inject.Inject;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public abstract class BaseFragment<T extends ComponentViewModel> extends Fragment {

    @Inject
    T viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectFragment();

        ((BaseActivity)getActivity()).getStateFragment().registerComponent(viewModel);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.onDisplayed();
    }

    @Override
    public void onPause() {
        super.onPause();

        viewModel.onHidden();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        DemoApp.get(getContext()).getLeakCanary().watch(this);
    }

    public T getViewModel() {
        return viewModel;
    }

    public ActivityComponent getInjection() {
        return ((BaseActivity)getActivity()).getStateFragment().getInjection();
    }

    public abstract void injectFragment();
}
