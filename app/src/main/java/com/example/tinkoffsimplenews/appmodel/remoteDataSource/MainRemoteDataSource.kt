package com.example.tinkoffsimplenews.appmodel.remoteDataSource

import android.util.Log
import com.example.tinkoffsimplenews.datapojo.NewsPOJO
import com.example.tinkoffsimplenews.datapojo.NewsPreviewPOJO
import io.reactivex.Maybe
import javax.inject.Inject

class MainRemoteDataSource @Inject constructor() : NewsRemoteDataSource {

    // Private Fields
    private val newsApiService = NewsApiService()

    // Public Fun
    override fun getNewsPreviews(): Maybe<List<NewsPreviewPOJO>> {
        return Maybe.just(Unit)
            .flatMap { loadNewsPreviews() }
    }
    override fun getNews(newsId: Long): Maybe<NewsPOJO> {
        return Maybe.just(Unit)
            .flatMap { loadNews(newsId) }
    }

    // Private Fun
    private fun loadNewsPreviews(): Maybe<List<NewsPreviewPOJO>> {
        val response =
            newsApiService.loadNewsPreviews()

        if (response.code() in 200..299)
            if (response.body()?.resultCode == "OK") {
                Log.d("REMOTE_DATA_SOURCE", "loadNewsPreviews: Success")
                return Maybe.just(response.body()!!.newsPreview)
            }

        Log.d("REMOTE_DATA_SOURCE", "loadNewsPreviews: Empty")
        return  Maybe.empty()
    }
    private fun loadNews(newsId: Long): Maybe<NewsPOJO> {
        val response =
            newsApiService.loadNews(newsId)

        if (response.code() in 200..299)
            if (response.body()?.resultCode == "OK") {
                Log.d("REMOTE_DATA_SOURCE", "loadNews: Success")
                return Maybe.just(response.body()!!.news)
            }

        Log.d("REMOTE_DATA_SOURCE", "loadNews: Empty")
        return Maybe.empty()
    }
}