package com.example.joao.photoscodechallenge

import com.example.joao.photoscodechallenge.entry.Photo
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
class MyCompose: ObservableTransformer<List<Photo>, State> {

    override fun apply(upstream: Observable<List<Photo>>): ObservableSource<State> {

        return upstream.
                map {
                    photos: List<Photo> ->
                    State.Success(photos) as State
                }
                .onErrorResumeNext { error: Throwable ->
                    Observable.just(State.Error(error as Exception))
                }
    }
}