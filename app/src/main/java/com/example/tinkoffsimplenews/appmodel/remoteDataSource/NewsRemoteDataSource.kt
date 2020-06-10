package com.example.tinkoffsimplenews.appmodel.remoteDataSource

import com.example.tinkoffsimplenews.datapojo.NewsPOJO
import com.example.tinkoffsimplenews.datapojo.NewsPreviewPOJO
import io.reactivex.Maybe

interface NewsRemoteDataSource {
    fun getNewsPreviews(): Maybe<List<NewsPreviewPOJO>>
    fun getNews(newsId: Long): Maybe<NewsPOJO>
}