package com.example.tinkoffsimplenews.app

import android.app.Application
import com.example.tinkoffsimplenews.dagger.AppComponent
import com.example.tinkoffsimplenews.dagger.AppModule
import com.example.tinkoffsimplenews.dagger.DaggerAppComponent

class App:Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}