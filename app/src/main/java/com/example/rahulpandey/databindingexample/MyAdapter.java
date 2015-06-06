package com.example.rahulpandey.databindingexample;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final ImageLoader imageLoader;

    public MyAdapter(Context context) {
        imageLoader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.list_item, viewGroup, false);
        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.getBinding().setVariable(com.example.rahulpandey.databindingexample.BR.img, Images.imageThumbUrls[i]);
        viewHolder.getBinding().setVariable(com.example.rahulpandey.databindingexample.BR.imageLoader, imageLoader);
        viewHolder.getBinding().executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return Images.imageThumbUrls.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }


    @BindingAdapter({"bind:imageUrl", "bind:imageLoader"})
    public static void loadImages(MyImageView imageView, String url, ImageLoader imageLoader) {
        imageView.setImageUrl(url, imageLoader);
    }


}
