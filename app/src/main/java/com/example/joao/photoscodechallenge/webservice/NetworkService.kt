package com.example.joao.photoscodechallenge.webservice

import android.net.ConnectivityManager

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
class NetworkService(var connectivityManager: ConnectivityManager) {

    val isConnected: Boolean
        get() {
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
}