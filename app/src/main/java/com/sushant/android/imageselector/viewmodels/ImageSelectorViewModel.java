package com.sushant.android.imageselector.viewmodels;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sushant.android.imageselector.R;
import com.sushant.android.imageselector.models.ImageModel;
import com.sushant.android.imageselector.ui.DetailActivity;

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

    public String getHighResImageUrl() {
        return imageModel.getWebformatURL();
    }

    public String getFavorites() {
        return imageModel.getFavorites();
    }

    //Load the images and use placeholder image while downloading the image.
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(view);
    }

    public View.OnClickListener openDetails() {
        return v -> {
            Intent i = new Intent(v.getContext(), DetailActivity.class);
            String serialized = new Gson().toJson(imageModel);
            i.putExtra(DetailActivity.SELECTED_IMAGE, serialized);
            v.getContext().startActivity(i);
        };
    }
}
