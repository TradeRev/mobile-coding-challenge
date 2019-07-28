package com.traderev.codingchallenge.network

import com.traderev.codingchallenge.model.Photos
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface to define all possible API end points
 * and their respective parameters
 */
interface PhotoService {

    //API end point to get photos in paginated order
    @GET("photos")
    fun getPhotos(
        @Query("client_id") clientID: String, @Query("page") page: Int, @Query("per_page") perPage: Int, @Query(
            "order_by"
        ) orderBy: String
    ): Single<List<Photos>>

}