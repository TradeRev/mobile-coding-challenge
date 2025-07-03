package com.example.joao.photoscodechallenge.service

import com.example.joao.photoscodechallenge.transformer.SchedulerTransformer
import com.example.joao.photoscodechallenge.webservice.MyWebServiceAPI
import com.example.joao.photoscodechallenge.webservice.payload.MyResponseObject
import io.reactivex.Observable

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
class MyService (val webServiceAPI: MyWebServiceAPI) {

    fun loadPhotos(pageCount: Int): Observable<List<MyResponseObject>> {
        return webServiceAPI.
                loadPhotos("a8861af9539a7fce15f9ca4cb443d62423000e32fb5db3377c7209e08f16ca3a", pageCount)
                .compose(SchedulerTransformer())
    }
}