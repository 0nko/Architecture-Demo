package com.ondrejruttkay.architecturedemo.viewmodel;

import com.ondrejruttkay.architecturedemo.common.di.PerActivity;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by Onko on 1/13/2017.
 */
@PerActivity
public class ComponentViewModel extends BaseViewModel {

    @Inject
    public ComponentViewModel(Bus bus) {
        super(bus);
    }
}
