package com.stockapp.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.stockapp.R;

import java.io.ByteArrayOutputStream;


public class ImageUtils {

    private static RequestOptions requestOptions;
    private static RequestOptions availableMerchantRequestOptions;

    private static RequestOptions getRequestOptions() {
        if (requestOptions == null)
            requestOptions = RequestOptions.placeholderOf(R.drawable.image_place_holder).error(R.drawable.image_place_holder);
        return requestOptions;
    }

    private static int getRandomAvatar() {
        return R.drawable.image_place_holder;
    }

    public static RequestOptions getAvailableMerchantRequestOptions() {
        if (availableMerchantRequestOptions == null)
            availableMerchantRequestOptions = RequestOptions.placeholderOf(R.drawable.image_place_holder).error(getRandomAvatar());
        return availableMerchantRequestOptions;
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).setDefaultRequestOptions(getRequestOptions()).load(imageUrl).into(view);
    }

    @BindingAdapter("loadAvailableMerchantIcon")
    public static void loadAvailableMerchantIcon(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).setDefaultRequestOptions(getAvailableMerchantRequestOptions()).load(imageUrl).into(view);
    }

    public static Bitmap getCompressedBitmap(Activity activity, Uri uri) {
        return getCompressedBitmap(activity, uri.toString());
    }

    public static Bitmap getCompressedBitmap(Activity activity, String uriString) {
//        Compressing Images
        Uri uri = Uri.parse(uriString);
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);
            final byte[] byteArray = baos.toByteArray();
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
