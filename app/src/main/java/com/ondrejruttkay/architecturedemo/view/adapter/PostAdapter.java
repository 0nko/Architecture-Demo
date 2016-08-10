package com.ondrejruttkay.architecturedemo.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ondrejruttkay.architecturedemo.BR;
import com.ondrejruttkay.architecturedemo.R;
import com.ondrejruttkay.architecturedemo.databinding.ItemPostBinding;
import com.ondrejruttkay.architecturedemo.viewmodel.PostViewModel;

import java.util.List;

/**
 * Created by Ondrej Ruttkay on 08/09/2016.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.BindingHolder> {

    private List<PostViewModel> posts;

    public PostAdapter(@NonNull List<PostViewModel> posts) {
        this.posts = posts;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPostBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_post, parent, false);
        return new BindingHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final PostViewModel post = posts.get(position);
        holder.binding.setVariable(BR.postViewModel, post);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding binding;

        BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }
    }
}
