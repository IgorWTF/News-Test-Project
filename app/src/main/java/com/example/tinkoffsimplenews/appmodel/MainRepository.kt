package com.example.tinkoffsimplenews.appmodel

import android.util.Log
import com.example.tinkoffsimplenews.app.App
import com.example.tinkoffsimplenews.appmodel.localDataSource.NewsLocalDataSource
import com.example.tinkoffsimplenews.appmodel.remoteDataSource.NewsRemoteDataSource
import com.example.tinkoffsimplenews.datamodel.News
import com.example.tinkoffsimplenews.datamodel.NewsPreview
import com.example.tinkoffsimplenews.service.DataMapperService
import com.example.tinkoffsimplenews.service.NetService
import io.reactivex.Maybe
import javax.inject.Inject


class MainRepository @Inject constructor() : NewsRepository {
    // -----------------------------------------------------------------------------------
    // Init
    init {
        App.appComponent.inject(this)
    }

    // -----------------------------------------------------------------------------------
    // Public Fields
    @Inject lateinit var netService: NetService
    @Inject lateinit var newsLocalDataSource: NewsLocalDataSource
    @Inject lateinit var newsRemoteDataSource: NewsRemoteDataSource


    // -----------------------------------------------------------------------------------
    // NewsRepository implementation
    override fun getNewsPreviews(): Maybe<List<NewsPreview>> {
        Log.d("MAIN_REPOSITORY", "getNewsPreviews")
        return newsLocalDataSource.getNewsPreviews()
            .map { DataMapperService.mapNewsPreviewEntityToModel(it) }
            .map { sortNewsPreviewsByPublicationDate(it) }
            .switchIfEmpty(
                newsRemoteDataSource.getNewsPreviews()
                    .map { DataMapperService.mapNewsPreviewPojoToModel(it) }
                    .doOnSuccess { saveNewsPreviewsToLocalDataSource(it) }
                    .map { sortNewsPreviewsByPublicationDate(it) }
            )
    }
    override fun getNews(newsId: Long):  Maybe<News> {
        Log.d("MAIN_REPOSITORY", "getNews")
        return newsLocalDataSource.getNews(newsId)
            .map { DataMapperService.mapNewsEntityToModel(it) }
            .switchIfEmpty(
                newsRemoteDataSource.getNews(newsId)
                    .map { DataMapperService.mapNewsPojoToModel(it) }
                    .doOnSuccess { saveNewsToLocalDataSource(it) }
            )
    }

    override fun updateNewsPreviews():  Maybe<List<NewsPreview>> {
        if (!netService.hasNetwork()) {
            //Toast.makeText(appContext, "Не удалось обновить данные.", Toast.LENGTH_SHORT).show()
            return newsLocalDataSource.getNewsPreviews()
                .map { DataMapperService.mapNewsPreviewEntityToModel(it) }
                .map { sortNewsPreviewsByPublicationDate(it) }
        }

        return newsRemoteDataSource.getNewsPreviews()
            .map { DataMapperService.mapNewsPreviewPojoToModel(it) }
            .doOnSuccess { saveNewsPreviewsToLocalDataSource(it) }
            .map { sortNewsPreviewsByPublicationDate(it) }
    }
    override fun updateNews(newsId: Long):  Maybe<News>{
        if(!netService.hasNetwork()) {
            //Toast.makeText(appContext, "Не удалось обновить данные.", Toast.LENGTH_SHORT).show()
            return newsLocalDataSource.getNews(newsId)
                .map { DataMapperService.mapNewsEntityToModel(it) }
        }

        return newsRemoteDataSource.getNews(newsId)
            .map { DataMapperService.mapNewsPojoToModel(it) }
            .doOnSuccess { saveNewsToLocalDataSource(it) }
    }

    // -----------------------------------------------------------------------------------
    // Private Fun
    private fun sortNewsPreviewsByPublicationDate(newsPreviews: List<NewsPreview>) : List<NewsPreview> {
        Log.d("MAIN_REPOSITORY", "sortedNewsPreviews (${newsPreviews.size})")
        return newsPreviews.sortedBy { -it.publicationDate }
    }

    private fun saveNewsPreviewsToLocalDataSource(newsPreviews: List<NewsPreview>) {
        newsLocalDataSource
            .saveNewsPreviews(DataMapperService.mapNewsPreviewModelToEntity(newsPreviews))
    }
    private fun saveNewsToLocalDataSource(news: News) {
        newsLocalDataSource
            .saveNews(DataMapperService.mapNewsModelToEntity(news))
    }
}