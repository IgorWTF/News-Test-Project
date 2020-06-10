package com.example.tinkoffsimplenews.appmodel.localDataSource

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity
import io.reactivex.Maybe
import javax.inject.Inject


class MainLocalDataSource @Inject constructor(private val application: Application):
    NewsLocalDataSource {
    // Private Fields
    private val newsDataBase: NewsDataBase =
        Room.databaseBuilder(application, NewsDataBase::class.java, "CacheDataBase").build()

    // NewsLocalDataSource Implementation
    override fun getNewsPreviews(): Maybe<List<NewsPreviewEntity>> {
        return Maybe.just(Unit)
            .flatMap { loadNewsPreviews() }
    }

    override fun getNews(newsId: Long): Maybe<NewsEntity> {
        return Maybe.just(Unit)
            .flatMap { loadNews(newsId) }
    }

    override fun saveNewsPreviews(newsPreviewsEntity: List<NewsPreviewEntity>) {
        saveNewsPreviewsToBd(newsPreviewsEntity)
    }

    override fun saveNews(news: NewsEntity) {
        saveNewsToBd(news)
    }

    // Private Fun
    private fun loadNewsPreviews(): Maybe<List<NewsPreviewEntity>> {
        val newsPreviews = newsDataBase.newsPreviewDao()?.getNewsPreview()

        if (newsPreviews != null) {
            if (newsPreviews.isNotEmpty()) {
                Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Success")
                return Maybe.just(newsPreviews)
            }
        }

        Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Empty")
        return Maybe.empty()
    }

    private fun loadNews(newsId: Long): Maybe<NewsEntity> {
        val news = newsDataBase.newsDao()?.getNews(newsId)

        if (news != null) {
            Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Success")
            return Maybe.just(news)
        }

        Log.d("LOCAL_DATA_SOURCE", "loadNews: Empty")
        return Maybe.empty()
    }

    private fun saveNewsPreviewsToBd(newsPreviewsEntity: List<NewsPreviewEntity>) {
        newsDataBase.newsPreviewDao()?.saveNewsPreviews(newsPreviewsEntity)
    }

    private fun saveNewsToBd(news: NewsEntity) {
        newsDataBase.newsDao()?.saveNews(news)
    }
}