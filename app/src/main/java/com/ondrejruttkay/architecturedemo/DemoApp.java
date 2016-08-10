package com.ondrejruttkay.architecturedemo;

import android.app.Application;
import android.content.Context;

import com.ondrejruttkay.architecturedemo.common.di.AppComponent;
import com.ondrejruttkay.architecturedemo.common.di.AppModule;
import com.ondrejruttkay.architecturedemo.common.di.DaggerAppComponent;
import com.ondrejruttkay.architecturedemo.common.util.FontCache;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Created by Onko on 08/09/2016.
 */
public class DemoApp extends Application {

    private AppComponent appComponent;
    private RefWatcher leakCanary;

    @Override
    public void onCreate() {
        super.onCreate();

        leakCanary = LeakCanary.install(this);
        FontCache.initialize(getApplicationContext());

        initAppComponent();
        initLogging();
    }

    public static DemoApp get(Context context) {
        return (DemoApp)context.getApplicationContext();
    }

    private void initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public RefWatcher getLeakCanary() {
        return leakCanary;
    }
}