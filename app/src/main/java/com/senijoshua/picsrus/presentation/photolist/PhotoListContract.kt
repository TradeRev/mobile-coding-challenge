package com.senijoshua.picsrus.presentation.photolist

interface PhotoListContract {

    interface PhotoView{
        fun onPhotoListLoaded()
        fun photoListLoadError()
    }

    interface PhotoPresenter{
        fun loadPhotoList()
        fun loadMorePhotos()
    }
}