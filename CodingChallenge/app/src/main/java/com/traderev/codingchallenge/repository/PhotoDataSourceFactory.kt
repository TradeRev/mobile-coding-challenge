package com.traderev.codingchallenge.repository


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.traderev.codingchallenge.model.Photos
import com.traderev.codingchallenge.network.PhotoService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PhotoDataSourceFactory @Inject constructor(
    private val compositeDisposable: CompositeDisposable, private val photoService: PhotoService
) : DataSource.Factory<Int, Photos>() {

    val photoDataSourceLiveData = MutableLiveData<PhotoDataSource>()

    override fun create(): DataSource<Int, Photos> {
        val photoDataSource = PhotoDataSource(photoService, compositeDisposable)
        photoDataSourceLiveData.postValue(photoDataSource)
        return photoDataSource
    }
}
