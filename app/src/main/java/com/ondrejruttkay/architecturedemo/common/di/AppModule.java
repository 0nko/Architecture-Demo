package com.ondrejruttkay.architecturedemo.common.di;

import android.content.Context;

import com.ondrejruttkay.architecturedemo.DemoApp;
import com.ondrejruttkay.architecturedemo.common.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.common.repository.IRepository;
import com.ondrejruttkay.architecturedemo.common.repository.Repository;
import com.ondrejruttkay.architecturedemo.common.localization.Localization;
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
