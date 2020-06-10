package com.example.tinkoffsimplenews.dagger

import com.example.tinkoffsimplenews.appmodel.MainRepository
import com.example.tinkoffsimplenews.appmodel.localDataSource.MainLocalDataSource
import com.example.tinkoffsimplenews.appmodel.remoteDataSource.MainRemoteDataSource
import com.example.tinkoffsimplenews.appviewmodel.MainViewModel
import com.example.tinkoffsimplenews.service.MainNetService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainRepository: MainRepository)
    fun inject(mainViewModel: MainViewModel)
    fun inject(mainLocalDataSource: MainLocalDataSource)
    fun inject(mainRemoteDataSource: MainRemoteDataSource)
    fun inject(mainNetService: MainNetService)
}