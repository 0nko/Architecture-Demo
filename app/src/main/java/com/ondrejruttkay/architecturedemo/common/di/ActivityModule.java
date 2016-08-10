package com.ondrejruttkay.architecturedemo.common.di;

import com.ondrejruttkay.architecturedemo.view.fragment.StateFragment;
import com.ondrejruttkay.architecturedemo.common.navigation.INavigator;
import com.ondrejruttkay.architecturedemo.common.navigation.Navigator;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module containing providers for dependencies needed throughout the life of an activity.
 */
@Module
public class ActivityModule {

    private StateFragment stateFragment;

    public ActivityModule(StateFragment stateFragment) {
        this.stateFragment = stateFragment;
    }

    @PerActivity
    @Provides
    public StateFragment provideStateFragment() {
        return stateFragment;
    }

    @PerActivity
    @Provides
    public INavigator provideNavigator(StateFragment stateFragment) {
        return new Navigator(stateFragment);
    }
}
