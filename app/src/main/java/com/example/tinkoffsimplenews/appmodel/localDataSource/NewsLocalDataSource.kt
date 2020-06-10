package com.example.tinkoffsimplenews.appmodel.localDataSource

import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity
import io.reactivex.Maybe

interface NewsLocalDataSource {
    fun getNewsPreviews(): Maybe<List<NewsPreviewEntity>>
    fun getNews(newsId: Long): Maybe<NewsEntity>

    fun saveNewsPreviews(newsPreviewsEntity: List<NewsPreviewEntity>)
    fun saveNews(news: NewsEntity)
}