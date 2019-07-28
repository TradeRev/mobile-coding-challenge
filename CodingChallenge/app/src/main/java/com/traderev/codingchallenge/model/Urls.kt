package com.traderev.codingchallenge.model

/**
 * Data class for capturing the response of Photos API
 * of Unsplash.
 */
data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)
