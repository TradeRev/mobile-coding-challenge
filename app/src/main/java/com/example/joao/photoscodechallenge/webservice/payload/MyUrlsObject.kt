package com.example.joao.photoscodechallenge.webservice.payload

import com.google.gson.annotations.SerializedName

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
data class MyUrlsObject (@SerializedName("small")val smallImage: String,
                         @SerializedName("regular") val regularImage: String)