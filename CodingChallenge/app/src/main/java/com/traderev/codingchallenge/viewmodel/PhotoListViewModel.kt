package com.traderev.codingchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.practice.gallery.common.State
import com.practice.gallery.model.Photos
import com.practice.gallery.repository.PhotoDataSource
import com.practice.gallery.repository.PhotoDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject




class PhotoListViewModel @Inject constructor(

    compositeDisposable: CompositeDisposable,

    photosDataSourceFactory: PhotoDataSourceFactory
) : ViewModel() {

    private var cDisposable: CompositeDisposable = compositeDisposable
    var photoList: LiveData<PagedList<Photos>>
    var photos: MutableLiveData<ArrayList<Photos>> = MutableLiveData()

    private val pageSize = 20

    private var photoDataSource: MutableLiveData<PhotoDataSource> = photosDataSourceFactory.photoDataSourceLiveData
    var responsePhotos: MutableLiveData<List<Photos>>? = MutableLiveData()

    init {


        responsePhotos = photoDataSource.value?.responsePhotos


        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(true)
            .build()
        photoList = LivePagedListBuilder(photosDataSourceFactory, config).build()


    }


    fun getState(): LiveData<State> = Transformations.switchMap<PhotoDataSource,
            State>(photoDataSource, PhotoDataSource::state)

    fun getResponse(): LiveData<List<Photos>> = Transformations.switchMap<PhotoDataSource,
            List<Photos>>(photoDataSource, PhotoDataSource::responsePhotos)

    fun retry() {
        photoDataSource.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return photoList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        cDisposable.dispose()
    }
}