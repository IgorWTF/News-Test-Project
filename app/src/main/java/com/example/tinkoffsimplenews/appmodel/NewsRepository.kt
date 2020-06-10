package com.example.tinkoffsimplenews.appmodel

import com.example.tinkoffsimplenews.datamodel.News
import com.example.tinkoffsimplenews.datamodel.NewsPreview
import io.reactivex.Maybe

interface NewsRepository {
    fun getNewsPreviews(): Maybe<List<NewsPreview>>
    fun getNews(newsId: Long):  Maybe<News>
    fun updateNewsPreviews():  Maybe<List<NewsPreview>>
    fun updateNews(newsId: Long):  Maybe<News>
}