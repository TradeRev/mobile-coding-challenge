package com.senijoshua.picsrus.presentation

import com.senijoshua.picsrus.data.models.Photos

object SharedStates{
    var currentPhotoList: List<Photos> = ArrayList()
    var currentListPosition: Int = 0
    var shouldLoad: Boolean = true
}