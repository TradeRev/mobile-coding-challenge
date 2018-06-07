package com.example.joao.photoscodechallenge.webservice

import com.example.joao.photoscodechallenge.webservice.payload.MyResponseObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
interface MyWebServiceAPI {

    @GET("photos")
    fun loadPhotos(@Query("client_id") id: String,@Query("page") pageCount: Int): Observable<List<MyResponseObject>>

}