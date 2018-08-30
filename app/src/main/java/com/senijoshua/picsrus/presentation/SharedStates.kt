package com.senijoshua.picsrus.presentation

import com.senijoshua.picsrus.data.models.Photos

object SharedStates{
    var currentPhotoList: List<Photos> = ArrayList()
    var currentListPosition: Int = 0
    var shouldLoad: Boolean = true
    var pageNumber: Int = 1
    val AUTH_ID = "5cc92c8dccfe857b9955a2d690140ccbbda7b0297d244b814d811b04a69f6559"
    val KEY_RELOAD_BUFFER = "RELOAD_BUFFER"
    val DETAIL_PHOTO = "DETAIL_PHOTO"
}