package com.senijoshua.picsrus.data.models

data class Photos(val id: String,
                  val width: Int,
                  val height: Int,
                  val color: String,
                  val likes: Int,
                  val user: User,
                  val urls: Urls)

data class Urls(val raw: String,
                val full: String,
                val regular: String,
                val small: String,
                val thumb: String)

data class User(val id: String,
                val name: String,
                val portfolio_url: String,
                val instagram_username: String,
                val twitter_username: String)

data class APIError(val errors: List<String>)

