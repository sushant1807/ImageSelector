package com.sushant.android.imageselector.viewmodels;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.sushant.android.imageselector.R;
import com.sushant.android.imageselector.models.ImageModel;

public class ImageSelectorViewModel extends BaseObservable {
    private ImageModel imageModel;

    public ImageSelectorViewModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public String getTags() {
        return imageModel.getTags();
    }

    public String getImageUrl() {
        return imageModel.getPreviewURL();
    }

    public String getUserName() {
        return imageModel.getUser();
    }

    public String getLikes() {
        return imageModel.getLikes();
    }

    public String getViews() {
        return imageModel.getViews();
    }

    //Load the images and use placeholder image while downloading the image.
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(view);
    }
}
