package com.senijoshua.picsrus.presentation.photolist

import com.senijoshua.picsrus.PicsRusApplication
import com.senijoshua.picsrus.data.repo.PhotoRepoImpl
import com.senijoshua.picsrus.utils.ErrorHandler
import rx.Scheduler

class PhotoListPresenter(private val photoRepo: PhotoRepoImpl,
                         private val viewInstance: PhotoListContract.PhotoView,
                         private val resultThread: Scheduler,
                         private val processThread: Scheduler) : PhotoListContract.PhotoPresenter {

    override fun loadPhotoList(pageNumber: Int) {
        photoRepo.getPhotosList(pageNumber)
                .subscribeOn(processThread)
                .observeOn(resultThread)
                .subscribe({
                    viewInstance.onPhotoListLoaded(it)
                }, {
                    it.printStackTrace()
                    viewInstance.photoListLoadError(ErrorHandler
                            .onError(PicsRusApplication.getApplicationContext(), it))

                })
    }
}