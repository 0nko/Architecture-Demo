package com.ondrejruttkay.architecturedemo.common.localization;

import android.content.Context;
import android.content.res.Configuration;

import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.common.event.LanguageChanged;
import com.squareup.otto.Bus;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Onko on 08/09/2016.
 */
@Singleton
public class FakeLocalization implements ILocalization {

    public static final String ENGLISH = "ENGLISH";
    public static final String SPANISH = "SPANISH";

    private Bus bus;
    private boolean isDefault;
    private String language;

    @Inject
    public FakeLocalization(Bus bus) {
        this.bus = bus;
        this.isDefault = true;
        this.language = ENGLISH;
    }

    @Override
    public String getEditButtonLabel() {
        return language;
    }

    @Override
    public String getLoadButtonLabel() {
        return language;
    }

    @Override
    public String getDeleteButtonLabel() {
        return language;
    }

    @Override
    public String getSaveButtonLabel() {
        return language;
    }

    @Override
    public String getTitleHint() {
        return language;
    }

    @Override
    public String getSummaryHint() {
        return language;
    }

    @Override
    public String getLanguageTitle() {
        return language;
    }

    @Override
    public void toggleLanguage() {
        isDefault = !isDefault;
        updateLocale();
        bus.post(new LanguageChanged());
    }

    @Override
    public void updateLocale() {
        if (isDefault) {
            this.language = ENGLISH;
        } else {
            this.language = SPANISH;
        }
    }
}
