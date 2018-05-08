package com.example.joao.photoscodechallenge

import okhttp3.Interceptor

/**
 * Created by Joao Alvares Neto on 07/05/2018.
 */
class RequestInterceptorMock constructor(private val exception: Exception): Interceptor {
    override fun intercept(chain: Interceptor.Chain) = throw exception
}