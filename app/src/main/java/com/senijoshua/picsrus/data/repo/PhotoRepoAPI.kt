package com.senijoshua.picsrus.data.repo

import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.presentation.SharedStates.AUTH_ID
import com.senijoshua.picsrus.utils.Mockable
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
@Mockable
interface PhotoRepoAPI {

    @GET("/photos")
    fun getPhotos(@Query("page") pageNumber: Int, @Query("per_page") itemsPerPage: Int = 20,
                  @Query("order_by") order: String = "popular",
                  @Query("client_id") clientId: String = AUTH_ID) : Observable<List<Photos>>

}