package com.sushant.android.imageselector.api_services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServiceModule {

    public static ImagesApiService createAPIService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com/");

        return builder.build().create(ImagesApiService.class);
    }
}
