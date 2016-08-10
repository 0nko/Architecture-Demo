package com.ondrejruttkay.architecturedemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ondrejruttkay.architecturedemo.DemoApp;
import com.ondrejruttkay.architecturedemo.common.di.ActivityComponent;
import com.ondrejruttkay.architecturedemo.viewmodel.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class StateFragment<T extends BaseViewModel> extends Fragment {

    private ActivityComponent activityComponent;

    @Inject
    T viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (activityComponent != null) {
            setRetainInstance(true);
            viewModel.onCreate();
        }
    }

    public void setInjection(ActivityComponent injection) {
        activityComponent = injection;
    }

    public ActivityComponent getInjection() {
        return activityComponent;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        viewModel.onDestroy();

        DemoApp.get(getContext()).getLeakCanary().watch(activityComponent);
        DemoApp.get(getContext()).getLeakCanary().watch(this);
        DemoApp.get(getContext()).getLeakCanary().watch(viewModel);

        activityComponent = null;
    }
}
