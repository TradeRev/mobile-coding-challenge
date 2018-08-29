package com.blankmemo.splashrev.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hongyuchen on 2018-08-28.
 */

public class ApiFactory {
    public static final String BASE_URL = "http://api.unsplash.com/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
