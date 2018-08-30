package com.blankmemo.splashrev.api;

import com.blankmemo.splashrev.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hongyuchen on 2018-08-28.
 */

public class ApiFactory {
    private Retrofit.Builder mRetrofitBuilder;
    private OkHttpClient.Builder mHttpClientBuilder;
    private HttpLoggingInterceptor mLoggingInterceptor;


    public ApiFactory(Retrofit.Builder retrofitBuilder,OkHttpClient.Builder okHttpClient,HttpLoggingInterceptor loggingInterceptor){
        this.mRetrofitBuilder=retrofitBuilder;
        this.mHttpClientBuilder=okHttpClient;
        this.mLoggingInterceptor=loggingInterceptor;
    }

    public ApiInterface createApi(String url){
        //For debug only
        if (BuildConfig.DEBUG) {
            mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            if(!mHttpClientBuilder.interceptors().contains(mLoggingInterceptor)){
                mHttpClientBuilder.addInterceptor(mLoggingInterceptor);
            }
        }
        //Connection Time Out
        mHttpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);

        return mRetrofitBuilder.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(mHttpClientBuilder.build())
                .build().create(ApiInterface.class);
    }
}
