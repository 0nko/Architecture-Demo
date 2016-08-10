package com.ondrejruttkay.architecturedemo.localization;

/**
 * Created by Onko on 8/10/2016.
 */

public interface ILocalization {
    String getViewButtonLabel();
    String getEditButtonLabel();
    String getLoadButtonLabel();
    String getDeleteButtonLabel();

    void updateLocale();
    void toggleLanguage();
}
