package com.example.tinkoffsimplenews.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat
import com.example.tinkoffsimplenews.app.App
import javax.inject.Inject

class MainNetService @Inject constructor(): NetService {

    // Init
    init {
        App.appComponent.inject(this)
    }

    // Public Fields
    @Inject lateinit var appContext: Context

    // Public Fun
    override fun hasNetwork(): Boolean {
        val connMgr = ContextCompat.getSystemService(appContext, ConnectivityManager::class.java)
        val networkInfo: NetworkInfo? = connMgr?.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}