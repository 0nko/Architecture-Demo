package com.ondrejruttkay.architecturedemo.navigation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.ondrejruttkay.architecturedemo.activity.BaseActivity;
import com.ondrejruttkay.architecturedemo.fragment.StateFragment;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class Navigator implements INavigator {

    private StateFragment stateFragment;

    public Navigator(StateFragment stateFragment) {
        this.stateFragment = stateFragment;
    }

    public void showMessage(String message) {
        Toast.makeText(stateFragment.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        if (isReady()) {
            ((BaseActivity) stateFragment.getActivity()).saveResult();
            stateFragment.getActivity().finish();
        }
    }

    @Override
    public void showMainScreen() {
//        showActivity(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void openUrl(String url) {
        if (isReady()) {
            Activity activity = stateFragment.getActivity();
            try {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception e) {
                Toast.makeText(activity, "Unable to open URL", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startActivityForResult(Class<?> activity, int requestCode) {
        showActivity(activity, null, null, requestCode);
    }

    private void startActivityForResult(Intent intent, int requestCode) {
        if (isReady()) {
            stateFragment.getActivity().startActivityForResult(intent, requestCode);
        }
    }

    private void showActivity(Class<?> activity) {
        showActivity(activity, 0, null, -1);
    }

    private void showActivity(Class<?> activity, int flags) {
        showActivity(activity, flags, null, -1);
    }

    private void showActivity(Class<?> activity, Bundle bundle) {
        showActivity(activity, null, bundle, -1);
    }

    private void showActivity(Class<?> activity, Integer flags, Bundle bundle) {
        showActivity(activity, flags, bundle, -1);
    }

    private void showActivity(Class<?> activity, Integer flags, Bundle bundle, int requestCode) {
        if (isReady()) {
            Intent intent = new Intent(stateFragment.getActivity(), activity);
            if (flags != null)
                intent.setFlags(flags);
            if (bundle != null)
                intent.putExtras(bundle);

            if (requestCode == -1) {
                stateFragment.getActivity().startActivity(intent);
            } else {
                stateFragment.getActivity().startActivityForResult(intent, requestCode);
            }
        }
    }

    private boolean isReady() {
        return stateFragment.isAdded() && ((BaseActivity) stateFragment.getActivity()).isCreated();
    }
}
