package com.ondrejruttkay.architecturedemo.fragment;

import android.support.v4.app.Fragment;

import com.ondrejruttkay.architecturedemo.DemoApp;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();

        DemoApp.get(getContext()).getLeakCanary().watch(this);
    }
}
