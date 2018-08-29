package com.senijoshua.picsrus.presentation.photolist

import android.widget.ImageView

interface PhotoClickListener {
    fun onPhotoClicked(position: Int, sharedImageView: ImageView)
    fun onPhotoLoaded(position: Int)
}