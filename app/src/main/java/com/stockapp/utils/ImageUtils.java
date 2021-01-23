package com.stockapp.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.stockapp.R;


public class ImageUtils {

    private static RequestOptions requestOptions;

    private static RequestOptions getRequestOptions() {
        if (requestOptions == null)
            requestOptions = RequestOptions.placeholderOf(R.drawable.image_place_holder).error(R.drawable.image_place_holder);
        return requestOptions;
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).setDefaultRequestOptions(getRequestOptions()).load(imageUrl).into(view);
    }

}
