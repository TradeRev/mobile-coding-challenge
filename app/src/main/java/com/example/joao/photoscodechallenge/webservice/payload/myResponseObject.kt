package com.example.joao.photoscodechallenge.webservice.payload

import com.google.gson.annotations.SerializedName

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
data class MyResponseObject (val id: String,
                             @SerializedName("urls")
                             val urlsObject: MyUrlsObject)