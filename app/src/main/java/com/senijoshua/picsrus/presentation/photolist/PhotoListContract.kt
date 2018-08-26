package com.senijoshua.picsrus.presentation.photolist

import com.senijoshua.picsrus.data.models.Photos

interface PhotoListContract {

    interface PhotoView{
        fun onPhotoListLoaded(list: List<Photos>)
        fun photoListLoadError()
    }

    interface PhotoPresenter{
        fun loadPhotoList()
        fun loadMorePhotos()
    }
}