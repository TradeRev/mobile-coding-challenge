package com.traderev.codingchallenge.model

/**
 * Data class for capturing the response of Photos API
 * of Unsplash.
 */
data class Photos(
    val alt_description: String,
    val categories: List<Any>,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val sponsored: Boolean,
    val sponsored_impressions_id: String,
    val updated_at: String,
    val urls: Urls,
    val width: Int
)

