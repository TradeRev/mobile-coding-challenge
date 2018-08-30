package com.blankmemo.splashrev.api;

import com.blankmemo.splashrev.datamodel.PhotoData;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by hongyuchen on 2018-08-28.
 */

public interface ApiInterface {
 @GET("/photos/curated/")
 Single<List<PhotoData>> getPhotoData(@QueryMap Map<String,String> photosMap);

}
