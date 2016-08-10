package com.ondrejruttkay.architecturedemo.databinding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.ObservableList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.adapter.PostAdapter;
import com.ondrejruttkay.architecturedemo.util.FontCache;
import com.ondrejruttkay.architecturedemo.viewmodel.PostViewModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Custom binding adapters used in data-binding.
 */
public class BindingHelpers {

    @BindingAdapter("pictureUrl")
    public static void loadImage(ImageView imageView, Uri uri) {
        if (uri == null) {
            imageView.setVisibility(View.GONE);
        }
        else {
            Picasso.with(imageView.getContext())
                    .load(uri)
                    .fit()
                    .into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter("command")
    public static void bindCommand(View view, final Command command) {
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(v -> command.execute());
        }
        view.setEnabled(command.canExecute().get());
        view.setVisibility(command.isVisible().get() ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("font")
    public static void bindFont(TextView textView, String fontName) {
        textView.setTypeface(FontCache.getInstance().get(fontName));
    }

    @BindingAdapter("bindPosts")
    public static void bindPosts(RecyclerView view, ObservableList<PostViewModel> posts) {
        PostAdapter adapter = new PostAdapter(posts);
        view.setTag(posts);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter.notifyDataSetChanged();
    }

    @BindingConversion
    public static int convertBoolToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }
}
