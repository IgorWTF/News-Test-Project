package com.example.tinkoffsimplenews.appmodel.remoteDataSource

import com.example.tinkoffsimplenews.datapojo.NewsPreviewsResultPOJO
import com.example.tinkoffsimplenews.datapojo.NewsResultPOJO
import retrofit2.Response

interface NewsApiService {
    fun loadNewsPreviews(): Response<NewsPreviewsResultPOJO?>
    fun loadNews(newsId: Long): Response<NewsResultPOJO?>
}