package com.example.tinkoffsimplenews.dagger

import com.example.tinkoffsimplenews.appmodel.MainRepository
import com.example.tinkoffsimplenews.appviewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainRepository: MainRepository)
    fun inject(mainViewModel: MainViewModel)
}