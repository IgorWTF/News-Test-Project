package com.example.tinkoffsimplenews.dagger

import android.app.Application
import com.example.tinkoffsimplenews.app.App
import com.example.tinkoffsimplenews.appmodel.MainRepository
import com.example.tinkoffsimplenews.appmodel.NewsRepository
import com.example.tinkoffsimplenews.appmodel.localDataSource.MainLocalDataSource
import com.example.tinkoffsimplenews.appmodel.localDataSource.NewsLocalDataSource
import com.example.tinkoffsimplenews.appmodel.remoteDataSource.MainRemoteDataSource
import com.example.tinkoffsimplenews.appmodel.remoteDataSource.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application:Application) {
    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun provideLocalDataSource(): NewsLocalDataSource = MainLocalDataSource(application)

    @Singleton
    @Provides
    fun provideRemoteDataSource(): NewsRemoteDataSource = MainRemoteDataSource()

    @Singleton
    @Provides
    fun provideMainRepository(): NewsRepository = MainRepository()
}