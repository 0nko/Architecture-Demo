package com.ondrejruttkay.architecturedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ondrejruttkay.architecturedemo.DemoApp;
import com.ondrejruttkay.architecturedemo.di.ActivityModule;
import com.ondrejruttkay.architecturedemo.fragment.StateFragment;
import com.ondrejruttkay.architecturedemo.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.navigation.INavigator;
import com.ondrejruttkay.architecturedemo.viewmodel.BaseViewModel;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    private static final String TAG_STATE_FRAGMENT = "STATE_FRAGMENT";

    private StateFragment<T> stateFragment;

    @Inject
    protected Bus bus;

    @Inject
    protected T viewModel;

    @Inject
    protected ILocalization localization;

    private boolean isCreated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initStateFragment();
        injectActivity();

        localization.updateLocale();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        isCreated = true;
    }

    @Override
    protected void onResume() {
        bus.register(this);
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void setupToolbarWithBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract void injectStateFragment();

    protected abstract void injectActivity();

    public void saveResult() {
    }

    @SuppressWarnings("unchecked")
    private void initStateFragment() {
        FragmentManager fm = getSupportFragmentManager();
        stateFragment = (StateFragment<T>) fm.findFragmentByTag(TAG_STATE_FRAGMENT);

        if (stateFragment == null) {
            stateFragment = new StateFragment<>();
            createActivityModule();
            injectStateFragment();
            fm.beginTransaction().add(stateFragment, TAG_STATE_FRAGMENT).commit();
        } else if (stateFragment.getInjection() == null) {
            createActivityModule();
            injectStateFragment();
            stateFragment.onCreate(null);
        }
    }

    protected void createActivityModule() {
        stateFragment.setInjection(DemoApp.get(this).getAppComponent()
                .plus(new ActivityModule(stateFragment)));
    }

    public StateFragment<T> getStateFragment() {
        return stateFragment;
    }

    public boolean isCreated() {
        return isCreated;
    }
}
