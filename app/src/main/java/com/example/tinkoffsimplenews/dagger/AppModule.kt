package com.example.tinkoffsimplenews.dagger

import android.app.Application
import androidx.room.Room
import com.example.tinkoffsimplenews.app.App
import com.example.tinkoffsimplenews.appmodel.MainRepository
import com.example.tinkoffsimplenews.appmodel.NewsRepository
import com.example.tinkoffsimplenews.appmodel.localDataSource.MainDataBase
import com.example.tinkoffsimplenews.appmodel.localDataSource.MainLocalDataSource
import com.example.tinkoffsimplenews.appmodel.localDataSource.NewsDataBase
import com.example.tinkoffsimplenews.appmodel.localDataSource.NewsLocalDataSource
import com.example.tinkoffsimplenews.appmodel.remoteDataSource.MainApiService
import com.example.tinkoffsimplenews.appmodel.remoteDataSource.MainRemoteDataSource
import com.example.tinkoffsimplenews.appmodel.remoteDataSource.NewsApiService
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
    fun provideLocalDataSource(): NewsLocalDataSource = MainLocalDataSource()

    @Singleton
    @Provides
    fun provideRemoteDataSource(): NewsRemoteDataSource = MainRemoteDataSource()

    @Singleton
    @Provides
    fun provideMainRepository(): NewsRepository = MainRepository()

    @Singleton
    @Provides
    fun provideNewsApiService(): NewsApiService = MainApiService()

    @Singleton
    @Provides
    fun provideNewsDataBase(): NewsDataBase = Room.databaseBuilder(application, MainDataBase::class.java, "NewsDataBase").build()
}