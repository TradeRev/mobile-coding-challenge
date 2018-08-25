package com.senijoshua.picsrus.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory<T>(apiInterface: Class<T>) {
    private val apiUrl: String = "https://api.unsplash.com" //set header- Accept-Version: v1
    private val apiEndpointInterface = apiInterface

    fun initApiInterface(): T {
        return createRetrofitInstance().create(apiEndpointInterface)
    }

    private fun createRetrofitInstance(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val retrofitClient = Retrofit.Builder()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(httpLoggingInterceptor)

        return retrofitClient.baseUrl(apiUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient.build())
                .build()


    }
}