package com.example.tinkoffsimplenews.appmodel

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.tinkoffsimplenews.datamodel.News
import com.example.tinkoffsimplenews.datamodel.NewsPreview
import io.reactivex.Maybe


class MainRepository(private val application: Application) {
    // Private Fields
    private val localDataSource = LocalDataSource(application)
    private val remoteDataSource = RemoteDataSource()

    // Public Fun
    fun getNewsPreviews(): Maybe<List<NewsPreview>> {
        Log.d("MAIN_REPOSITORY", "getNewsPreviews")
        return localDataSource.getNewsPreviews()
            .map { DataMapperService.mapNewsPreviewEntityToModel(it) }
            .map { sortNewsPreviewsByPublicationDate(it) }
            .switchIfEmpty(
                remoteDataSource.getNewsPreviews()
                    .map { DataMapperService.mapNewsPreviewPojoToModel(it) }
                    .doOnSuccess { saveNewsPreviewsToLocalDataSource(it) }
                    .map { sortNewsPreviewsByPublicationDate(it) }
            )
    }
    fun getNews(newsId: Long):  Maybe<News> {
        Log.d("MAIN_REPOSITORY", "getNews")
        return localDataSource.getNews(newsId)
            .map { DataMapperService.mapNewsEntityToModel(it) }
            .switchIfEmpty(
                remoteDataSource.getNews(newsId)
                    .map { DataMapperService.mapNewsPojoToModel(it) }
                    .doOnSuccess { saveNewsToLocalDataSource(it) }
            )
    }

    fun updateNewsPreviews():  Maybe<List<NewsPreview>> {
        if (!hasNetwork()) {
            Toast.makeText(application, "Не удалось обновить данные.", Toast.LENGTH_SHORT).show()
            return localDataSource.getNewsPreviews()
                .map { DataMapperService.mapNewsPreviewEntityToModel(it) }
                .map { sortNewsPreviewsByPublicationDate(it) }
        }

        return remoteDataSource.getNewsPreviews()
            .map { DataMapperService.mapNewsPreviewPojoToModel(it) }
            .doOnSuccess { saveNewsPreviewsToLocalDataSource(it) }
            .map { sortNewsPreviewsByPublicationDate(it) }
    }
    fun updateNews(newsId: Long):  Maybe<News>{
        if(!hasNetwork()) {
            Toast.makeText(application, "Не удалось обновить данные.", Toast.LENGTH_SHORT).show()
            return localDataSource.getNews(newsId)
                .map { DataMapperService.mapNewsEntityToModel(it) }
        }

        return remoteDataSource.getNews(newsId)
            .map { DataMapperService.mapNewsPojoToModel(it) }
            .doOnSuccess { saveNewsToLocalDataSource(it) }
    }

    // Private Fun
    private fun sortNewsPreviewsByPublicationDate(newsPreviews: List<NewsPreview>) : List<NewsPreview> {
        Log.d("MAIN_REPOSITORY", "sortedNewsPreviews (${newsPreviews.size})")
        return newsPreviews.sortedBy { -it.publicationDate }
    }

    private fun saveNewsPreviewsToLocalDataSource(newsPreviews: List<NewsPreview>) {
        localDataSource
            .saveNewsPreviews(DataMapperService.mapNewsPreviewModelToEntity(newsPreviews))
    }
    private fun saveNewsToLocalDataSource(news: News) {
        localDataSource
            .saveNews(DataMapperService.mapNewsModelToEntity(news))
    }

    private fun hasNetwork(): Boolean {
        val connMgr = getSystemService(application,ConnectivityManager::class.java)
        val networkInfo: NetworkInfo? = connMgr?.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}