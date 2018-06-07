package com.example.joao.photoscodechallenge

import com.example.joao.photoscodechallenge.entry.Photo

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
sealed class State {

    class Loading: State()

    data class Error(val exception: Exception): State()

    data class Success(val photos: List<Photo>): State()

}