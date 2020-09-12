package com.sushant.android.imageselector.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sushant.android.imageselector.R;
import com.sushant.android.imageselector.databinding.ImageSelectorItemBinding;
import com.sushant.android.imageselector.models.ImageModel;
import com.sushant.android.imageselector.viewmodels.ImageSelectorViewModel;

import java.util.List;

public class ImageSelectorListAdapter extends RecyclerView.Adapter<ImageSelectorListAdapter.ImageSelectorViewHolder> {

    private List<ImageModel> imageList;

    public ImageSelectorListAdapter(List<ImageModel> imageList) {
        this.imageList = imageList;
    }

    @Override
    public ImageSelectorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageSelectorViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_selector_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageSelectorViewHolder holder, int position) {
        holder.imageSelectorItemBinding.setViewmodel(new ImageSelectorViewModel(imageList.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ImageSelectorViewHolder extends RecyclerView.ViewHolder {

        final ImageSelectorItemBinding imageSelectorItemBinding;

         ImageSelectorViewHolder(View v) {
            super(v);
            imageSelectorItemBinding = ImageSelectorItemBinding.bind(v);
        }
    }

}
