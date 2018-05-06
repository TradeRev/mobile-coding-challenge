package com.example.joao.photoscodechallenge.viewModel

import android.arch.lifecycle.ViewModel
import com.example.joao.photoscodechallenge.MyCompose
import com.example.joao.photoscodechallenge.MyTransformer
import com.example.joao.photoscodechallenge.State
import com.example.joao.photoscodechallenge.service.MyService
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
                .map { MyTransformer().convert(it) }
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
                .map { MyTransformer().convert(it) }
                .compose(MyCompose())
    }
}