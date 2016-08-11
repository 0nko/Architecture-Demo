package com.ondrejruttkay.architecturedemo.viewmodel;

import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.squareup.otto.Bus;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public abstract class BaseViewModel extends Observable.OnPropertyChangedCallback implements Observable {

    private transient PropertyChangeRegistry callbacks;
    private Bus bus;

    public BaseViewModel(Bus bus) {
        this.bus = bus;
    }

    protected Bus getBus() {
        return bus;
    }

    public void onCreate() {
        bus.register(this);
    }

    public void onDestroy() {
        bus.unregister(this);
    }

    public void onResume() {
    }

    public void onPause() {
    }

    //region Observable
    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (callbacks == null) {
            callbacks = new PropertyChangeRegistry();
        }
        callbacks.add(callback);
    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (callbacks != null) {
            callbacks.remove(callback);
        }
    }

    @Override
    public void onPropertyChanged(Observable observable, int i) {
        onPropertyChanged(observable);
    }

    public void onPropertyChanged(Observable observable) {
    }

    protected synchronized void notifyChange() {
        if (callbacks != null) {
            callbacks.notifyCallbacks(this, 0, null);
        }
    }

    protected void notifyPropertyChanged(int fieldId) {
        if (callbacks != null) {
            callbacks.notifyCallbacks(this, fieldId, null);
        }
    }

    //endregion
}
