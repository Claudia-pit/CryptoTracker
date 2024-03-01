package com.example.cryptotracker.net

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

//check if internet connection (Wifi/cellular) is available or not
fun checkForInternet(context: Context): Boolean {

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}

//Handle exception message
fun onExceptionOccurred(e: Exception) {
    when (e.message) {
        e.message?.contains("HTTP 429").toString() -> {
            Log.e("Exception 429", "${e.message}")
        }

        else -> Log.e("Exception", "${e.message}")

    }
}

