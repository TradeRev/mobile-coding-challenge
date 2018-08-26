package com.senijoshua.picsrus.data.repo

import com.senijoshua.picsrus.data.models.Photos
import rx.Observable

class PhotoRepoImpl(var photoApi: PhotoRepoAPI){

    fun getPhotosList(page: Int, perPage: Int): Observable<List<Photos>> {
        return photoApi.getPhotos(page, perPage)
    }

}