package com.ondrejruttkay.architecturedemo.common.databinding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.ObservableList;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ondrejruttkay.architecturedemo.common.util.FontCache;
import com.ondrejruttkay.architecturedemo.view.adapter.PostAdapter;
import com.ondrejruttkay.architecturedemo.viewmodel.PostViewModel;
import com.squareup.picasso.Picasso;

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
