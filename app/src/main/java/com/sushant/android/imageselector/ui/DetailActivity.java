package com.sushant.android.imageselector.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.sushant.android.imageselector.R;
import com.sushant.android.imageselector.databinding.ActivityDetailsBinding;
import com.sushant.android.imageselector.models.ImageModel;
import com.sushant.android.imageselector.viewmodels.ImageSelectorViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title_details)
    Toolbar toolbar;

    ActivityDetailsBinding activityDetailsBinding;
    public final static String SELECTED_IMAGE = "IMAGE_SELECTED";
    private ImageModel image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        ButterKnife.bind(this);
        initImage();
        activityDetailsBinding.setViewmodel(new ImageSelectorViewModel(image));

        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        /*toolbar.setTitle(R.string.app_name);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.ic__back_arrow);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());*/
    }

    private void initImage() {
        image = new Gson().fromJson(getIntent().getStringExtra(SELECTED_IMAGE), ImageModel.class);
    }
}
