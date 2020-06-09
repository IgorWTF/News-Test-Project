package com.example.tinkoffsimplenews.appmodel

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity
import io.reactivex.Maybe


class LocalDataSource(private val application: Application) {
    // Private Fields
    private val cacheDataBase: CacheDataBase =
        Room.databaseBuilder(application, CacheDataBase::class.java, "CacheDataBase").build()

    // Public Fun
    fun getNewsPreviews() : Maybe<List<NewsPreviewEntity>> {
        return Maybe.just(Unit)
            .flatMap { loadNewsPreviews() }
    }
    fun getNews(newsId:Long): Maybe<NewsEntity> {
        return Maybe.just(Unit)
            .flatMap { loadNews(newsId) }
    }

    fun saveNewsPreviews(newsPreviewsEntity:List<NewsPreviewEntity>) {
        saveNewsPreviewsToBd(newsPreviewsEntity)
    }
    fun saveNews(news: NewsEntity) {
        saveNewsToBd(news)
    }

    // Private Fun
    private fun loadNewsPreviews() : Maybe<List<NewsPreviewEntity>> {
        val newsPreviews = cacheDataBase.NewsPreviewDao()?.getNewsPreview()

        if (newsPreviews != null) {
            if(newsPreviews.isNotEmpty()) {
                Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Success")
                return Maybe.just(newsPreviews)
            }
        }

        Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Empty")
        return Maybe.empty()
    }
    private fun loadNews(newsId:Long): Maybe<NewsEntity> {
        val news = cacheDataBase.NewsDao()?.getNews(newsId)

        if (news != null) {
            Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Success")
            return Maybe.just(news)
        }

        Log.d("LOCAL_DATA_SOURCE", "loadNews: Empty")
        return Maybe.empty()
    }

    private fun saveNewsPreviewsToBd(newsPreviewsEntity:List<NewsPreviewEntity>) {
        cacheDataBase.NewsPreviewDao()?.saveNewsPreviews(newsPreviewsEntity)
    }
    private fun saveNewsToBd(news: NewsEntity) {
        cacheDataBase.NewsDao()?.saveNews(news)
    }
}