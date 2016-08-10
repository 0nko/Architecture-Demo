package com.ondrejruttkay.architecturedemo.localization;

import android.content.Context;
import android.content.res.Configuration;

import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.event.LanguageChanged;
import com.squareup.otto.Bus;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Onko on 08/09/2016.
 */
@Singleton
public class Localization implements ILocalization {

    private Context context;
    private Bus bus;
    private boolean isDefault;

    @Inject
    public Localization(Context context, Bus bus) {
        this.context = context;
        this.bus = bus;
        this.isDefault = true;
    }

    @Override
    public String getEditButtonLabel() {
        return context.getString(R.string.action_edit);
    }

    @Override
    public String getLoadButtonLabel() {
        return context.getString(R.string.action_load);
    }

    @Override
    public String getDeleteButtonLabel() {
        return context.getString(R.string.action_delete);
    }

    @Override
    public String getSaveButtonLabel() {
        return context.getString(R.string.action_save);
    }

    @Override
    public String getTitleHint() {
        return context.getString(R.string.title_hint);
    }

    @Override
    public String getSummaryHint() {
        return context.getString(R.string.summary_hint);
    }

    @Override
    public String getLanguageTitle() {
        return context.getString(R.string.language_title);
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
            Configuration conf = context.getResources().getConfiguration();
            conf.setLocale(Locale.getDefault());
            context.getResources().updateConfiguration(conf, null);
        } else {
            Configuration conf = context.getResources().getConfiguration();
            conf.setLocale(Locale.forLanguageTag("es"));
            context.getResources().updateConfiguration(conf, null);
        }
    }
}
