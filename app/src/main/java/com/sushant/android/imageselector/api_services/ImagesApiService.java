package com.sushant.android.imageselector.api_services;


import com.sushant.android.imageselector.models.PixabayImageList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImagesApiService {

    @GET("/api/")
    Call<PixabayImageList> getImageResults(@Query("key") String key, @Query("q") String query, @Query("page") int page, @Query("per_page") int perPage);
}
