package com.ondrejruttkay.architecturedemo.common.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component for dependency injection.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    ActivityComponent plus(ActivityModule activityModule);
}
