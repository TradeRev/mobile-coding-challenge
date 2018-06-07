package com.example.joao.photoscodechallenge.viewModel

import android.arch.lifecycle.ViewModel
import com.example.joao.photoscodechallenge.MyCompose
import com.example.joao.photoscodechallenge.State
import com.example.joao.photoscodechallenge.entry.Photo
import com.example.joao.photoscodechallenge.service.MyService
import com.example.joao.photoscodechallenge.webservice.payload.MyResponseObject
import io.reactivex.Observable

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
class MyViewModel (val myService: MyService): ViewModel() {

    private var pageCount = 1

    fun listPhotos(): Observable<State> {

        return myService
                .loadPhotos(pageCount)
                .map {
                    pageCount++
                    it
                }
                .map { it.toPhotos() }
                .compose(MyCompose())
                .startWith(State.Loading())
    }


    fun loadMore(): Observable<State> {

        return myService
                .loadPhotos(pageCount)
                .map {
                    pageCount++
                    it
                }
                .map { it.toPhotos() }
                .compose(MyCompose())
    }

    private fun List<MyResponseObject>.toPhotos() = map { it.toPhoto() }
    private fun MyResponseObject.toPhoto() = Photo(id,urlsObject.smallImage,urlsObject.regularImage)
}