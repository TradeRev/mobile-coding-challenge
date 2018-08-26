package com.senijoshua.picsrus.data.repo

import com.senijoshua.picsrus.data.models.Photos
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface PhotoRepoAPI {

    @GET("/photos")
    fun getPhotos(@Query("page") pageNumber: Int, @Query("per_page") itemsPerPage: Int,
                  @Query("order_by") order: String = "popular") : Observable<List<Photos>>

}