package com.ondrejruttkay.architecturedemo.localization;

/**
 * Created by Onko on 8/10/2016.
 */

public interface ILocalization {
    String getEditButtonLabel();
    String getLoadButtonLabel();
    String getDeleteButtonLabel();
    String getSaveButtonLabel();
    String getTitleHint();
    String getSummaryHint();
    String getLanguageTitle();

    void updateLocale();
    void toggleLanguage();

}
