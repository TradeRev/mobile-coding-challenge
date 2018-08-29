package com.senijoshua.picsrus.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos(val id: String,
                  val width: Int,
                  val height: Int,
                  val color: String,
                  val likes: Int,
                  val user: User,
                  val urls: Urls) : Parcelable
@Parcelize
data class Urls(val raw: String,
                val full: String,
                val regular: String,
                val small: String,
                val thumb: String) : Parcelable
@Parcelize
data class User(val id: String,
                val name: String,
                val portfolio_url: String,
                val instagram_username: String,
                val twitter_username: String) : Parcelable

data class APIError(val errors: List<String>)

