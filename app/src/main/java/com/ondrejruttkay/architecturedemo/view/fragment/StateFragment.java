package com.ondrejruttkay.architecturedemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ondrejruttkay.architecturedemo.DemoApp;
import com.ondrejruttkay.architecturedemo.common.di.ActivityComponent;
import com.ondrejruttkay.architecturedemo.viewmodel.BaseViewModel;
import com.ondrejruttkay.architecturedemo.viewmodel.ComponentViewModel;
import com.ondrejruttkay.architecturedemo.viewmodel.ScreenViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class StateFragment<T extends ScreenViewModel> extends Fragment {

    private ActivityComponent activityComponent;

    @Inject
    T viewModel;

    List<WeakReference<ComponentViewModel>> childViewModels = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (activityComponent != null) {
            setRetainInstance(true);
        }
    }

    public void setInjection(ActivityComponent injection) {
        activityComponent = injection;
    }

    public ActivityComponent getInjection() {
        return activityComponent;
    }

    public void registerComponent(ComponentViewModel componentViewModel) {
        childViewModels.add(new WeakReference<ComponentViewModel>(componentViewModel));
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

        viewModel.onDestroy();

        for (WeakReference<ComponentViewModel> vm : childViewModels) {
            if (vm.get() != null) {
                vm.get().onDestroy();
            }
        }

        DemoApp.get(getContext()).getLeakCanary().watch(activityComponent);
        DemoApp.get(getContext()).getLeakCanary().watch(this);
        DemoApp.get(getContext()).getLeakCanary().watch(viewModel);

        activityComponent = null;
    }
}
