package com.senijoshua.picsrus.utils

import android.content.Context
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.data.RetrofitFactory
import com.senijoshua.picsrus.data.models.APIError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import java.net.UnknownHostException
import okio.BufferedSource
import java.nio.charset.Charset
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException


class ErrorHandler {

    companion object {
        fun onError(context: Context, throwable: Throwable): String {
            var errorMessage = context.getString(R.string.unexpected_error)
            if (throwable is UnknownHostException) {
                errorMessage = context.getString(R.string.network_connection_error)
            }

            if (throwable is HttpException) {
                //chain errors together
                errorMessage = parseError(throwable.response().errorBody()?.string()).errors[0]
            }

            if (throwable is IOException){
                errorMessage = context.getString(R.string.network_connection_error)
            }

            return errorMessage
        }

        private fun parseError(responseBody: String?): APIError {
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter<APIError>(APIError::class.java)

            return jsonAdapter.fromJson(responseBody!!)!!
        }
    }

}