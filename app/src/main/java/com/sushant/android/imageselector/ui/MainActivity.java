package com.sushant.android.imageselector.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sushant.android.imageselector.R;
import com.sushant.android.imageselector.Utils.LocalConstant;
import com.sushant.android.imageselector.adapters.ImageSelectorListAdapter;
import com.sushant.android.imageselector.api_services.APIServiceModule;
import com.sushant.android.imageselector.api_services.InternetCheck;
import com.sushant.android.imageselector.models.ImageModel;
import com.sushant.android.imageselector.models.ImageModelList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_list)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar_title)
    Toolbar toolbar;
    @BindView(R.id.no_results_text)
    TextView noResults;

    private List<ImageModel> imageList;
    private ImageSelectorListAdapter imageSelectorAdapter;
    private MenuItem searchMenuItem;
    private String currentQuery = LocalConstant.DEFAULT_QUERY_TEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());
        initRecyclerView();
        initToolbar();
        initInstance();
    }

    private void initInstance() {
        if (!InternetCheck.isInternetAvailable(this)) {
            initSnackbar(R.string.msg_no_internet);
        }
        else  {
            loadImages(1, currentQuery);
        }
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        imageList = new ArrayList<>();
        imageSelectorAdapter = new ImageSelectorListAdapter(imageList);
        recyclerView.setAdapter(imageSelectorAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initSnackbar(int messageId) {
        progressBar.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar.make(recyclerView, messageId, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.msg_retry, v -> {
                    if (InternetCheck.isInternetAvailable(v.getContext())) {
                        resetImageList();
                        progressBar.setVisibility(View.VISIBLE);
                        loadImages(1, currentQuery);
                    } else {
                        initSnackbar(R.string.msg_no_internet);
                    }
                });
        snackbar.show();
    }

    private void loadImages(int page, String query) {
        APIServiceModule.createAPIService()
                .getImageResults(LocalConstant.API_KEY_VALUE, query, page, 30)
                .enqueue(new Callback<ImageModelList>() {
            @Override
            public void onResponse(Call<ImageModelList> call, Response<ImageModelList> response) {
                if (response.isSuccessful()) {
                    Timber.e("loadImages %s ", response.body().getHits());
                    addImagesToList(response.body());
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ImageModelList> call, Throwable t) {
                initSnackbar(R.string.msg_error_text);
            }
        });
    }

    private void addImagesToList(ImageModelList response) {
        progressBar.setVisibility(View.GONE);
        int position = imageList.size();
        imageList.addAll(response.getHits());
        imageSelectorAdapter.notifyItemRangeInserted(position, position + 20);
        if (imageList.isEmpty()) {
            Timber.e("Reached to addImagesToList No results Visible ");
            noResults.setVisibility(View.VISIBLE);
        } else {
            Timber.e("Reached to addImagesToList No results Invisible ");
            noResults.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(searchListener);
        return true;
    }

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchMenuItem.collapseActionView();
            currentQuery = query;
            resetImageList();
            progressBar.setVisibility(View.VISIBLE);
            noResults.setVisibility(View.GONE);
            loadImages(1, currentQuery);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void resetImageList() {
        imageList.clear();
        imageSelectorAdapter.notifyDataSetChanged();
    }
}
