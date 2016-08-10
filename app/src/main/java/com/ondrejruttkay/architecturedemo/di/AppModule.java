package com.ondrejruttkay.architecturedemo.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ondrejruttkay.architecturedemo.DemoApp;
import com.ondrejruttkay.architecturedemo.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.repository.IRepository;
import com.ondrejruttkay.architecturedemo.repository.Repository;
import com.ondrejruttkay.architecturedemo.localization.Localization;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module containing providers for all singleton dependencies needed throughout the app.
 */
@Module
public class AppModule {

    private final DemoApp app;

    public AppModule(DemoApp app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public IRepository provideRepository(Bus bus) {
        return new Repository(bus);
    }

    @Singleton
    @Provides
    public Context provideAppContext() {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    public Bus provideEventBus() {
        return new Bus();
    }

    @Singleton
    @Provides
    public ILocalization provideLocalization(Context context, Bus bus) {
        return new Localization(context, bus);
    }
}
