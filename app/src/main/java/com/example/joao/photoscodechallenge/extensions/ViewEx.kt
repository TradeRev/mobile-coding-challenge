package com.example.joao.photoscodechallenge.extensions

import android.view.View

/**
 * Created by Joao Alvares Neto on 06/05/2018.
 */
fun View.isVisible() = this.visibility == View.VISIBLE
fun View.isGone() = this.visibility == View.GONE

fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.gone(){
    this.visibility = View.GONE
}