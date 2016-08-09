package com.ondrejruttkay.architecturedemo.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by Onko on 08/09/2016.
 */
@Singleton
public class Utilities implements IUtilities {

    private Context context;

    @Inject
    public Utilities(Context context) {
        this.context = context;
    }

    @Override
    public String getVersion() {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (e.getMessage() != null)
                Timber.e(e.getMessage());
        }
        return "N/A";
    }
}
