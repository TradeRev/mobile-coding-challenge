package com.senijoshua.picsrus.presentation.photolist

import com.senijoshua.picsrus.BuildConfig
import com.senijoshua.picsrus.data.repo.PhotoRepoImpl
import com.senijoshua.picsrus.utils.MockImageRepo
import rx.Scheduler

class PhotoListPresenter(private val photoRepo: PhotoRepoImpl,
                         private val viewInstance: PhotoListContract.PhotoView,
                         private val resultThread: Scheduler,
                         private val processThread: Scheduler) : PhotoListContract.PhotoPresenter {

    var pageNumber: Int = 1
    var itemsPerPage: Int = 20

    override fun loadPhotoList() {
        if (BuildConfig.DEBUG){
            Thread.sleep(800)
            viewInstance.onPhotoListLoaded(MockImageRepo().getPhotoData())
        } else {
            photoRepo.getPhotosList(pageNumber, itemsPerPage)
                    .subscribeOn(processThread)
                    .observeOn(resultThread)
                    .subscribe({
                        viewInstance.onPhotoListLoaded(it)
                    }, {
                        it.printStackTrace()
                        viewInstance.photoListLoadError()
                    })
        }

    }

    override fun loadMorePhotos() {

    }


    fun getErrorBody(throwable: Throwable) : String{
        return ""//deal with getting the error body from the throwable later
    }
}