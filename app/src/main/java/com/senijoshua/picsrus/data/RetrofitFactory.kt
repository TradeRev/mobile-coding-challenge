package com.senijoshua.picsrus.data

import com.senijoshua.picsrus.BuildConfig
import com.senijoshua.picsrus.utils.GlobalConstants.AUTH_ID
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class RetrofitFactory {
    val API_URL: String = "https://api.unsplash.com"
    val HTTP_LOG_LEVEL = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    fun createRetrofitInstance(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val retrofitClient = Retrofit.Builder()

        httpLoggingInterceptor.level = HTTP_LOG_LEVEL
        okHttpClient.addInterceptor(httpLoggingInterceptor)

        return retrofitClient.baseUrl(API_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient.build())
                .build()


    }

}