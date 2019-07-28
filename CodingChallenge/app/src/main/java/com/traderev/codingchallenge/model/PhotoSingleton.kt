package com.traderev.codingchallenge.model


import androidx.lifecycle.MutableLiveData

object PhotoSingleton {

    var photos : MutableLiveData<ArrayList<Photos>> = MutableLiveData()

    fun setPhotos(updatedPhotos: ArrayList<Photos>){

        photos.value = updatedPhotos
    }

    fun getPhotos() : ArrayList<Photos>? {

        return photos.value
    }
}