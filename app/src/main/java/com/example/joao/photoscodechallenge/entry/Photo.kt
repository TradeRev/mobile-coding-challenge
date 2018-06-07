package com.example.joao.photoscodechallenge.entry

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
@Parcelize
data class Photo(val id: String, val smallUrl: String, val regularUrl: String): Parcelable