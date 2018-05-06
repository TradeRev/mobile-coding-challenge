package com.example.joao.photoscodechallenge

import com.example.joao.photoscodechallenge.entry.Photo
import com.example.joao.photoscodechallenge.webservice.payload.MyResponseObject

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
class MyTransformer {

    fun convert(myResponseObject: List<MyResponseObject>) : List<Photo> {

        val photos = arrayListOf<Photo>()

        for (item in myResponseObject){

            val id = item.id
            val regular = item.urlsObject.regularImage
            val small = item.urlsObject.smallImage
            photos.add(Photo(id, small, regular))
        }
        return photos
    }
}