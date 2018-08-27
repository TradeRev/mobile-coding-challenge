package com.senijoshua.picsrus.presentation.photolist

import android.view.View
import android.widget.ImageView
import com.senijoshua.picsrus.data.models.Photos

interface PhotoClickListener {
    fun onPhotoClicked(position: Int, sharedImageView: ImageView)
    fun onPhotoLoaded(position: Int)
}