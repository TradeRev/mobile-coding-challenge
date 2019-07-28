package com.traderev.codingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class PhotoListViewModelFactory @Inject constructor(
    private val photoListViewModel: PhotoListViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoListViewModel::class.java)) {
            return photoListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}