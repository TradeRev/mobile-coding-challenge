package com.senijoshua.picsrus.data.repo

import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.utils.Mockable
import rx.Observable
@Mockable
class PhotoRepoImpl(var photoApi: PhotoRepoAPI){

    fun getPhotosList(page: Int): Observable<List<Photos>> {
        return photoApi.getPhotos(page)
    }

}