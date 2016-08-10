package com.ondrejruttkay.architecturedemo.common.databinding;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import rx.functions.Action0;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class Command extends BaseObservable {

    private ObservableBoolean canExecute;
    private ObservableBoolean isVisible;
    private Action0 execute;

    public Command(Action0 execute) {
        this(true, execute);
    }

    public Command(boolean canExecute, Action0 execute) {
        this(canExecute, true, execute);
    }

    public Command(boolean canExecute, boolean isVisible, Action0 execute) {
        this.canExecute = new ObservableBoolean(canExecute);
        this.isVisible = new ObservableBoolean(isVisible);
        this.execute = execute;
    }

    public void execute() {
        execute.call();
    }

    public void setCanExecute(boolean canExecute) {
        if (this.canExecute.get() != canExecute) {
            this.canExecute.set(canExecute);
            notifyChange();
        }
    }

    public ObservableBoolean canExecute() {
        return canExecute;
    }

    public void setVisible(boolean isVisible) {
        if (this.isVisible.get() != isVisible) {
            this.isVisible.set(isVisible);
            notifyChange();
        }
    }

    public ObservableBoolean isVisible() {
        return isVisible;
    }
}
