package com.example.tinkoffsimplenews.appmodel.localDataSource

import android.util.Log
import com.example.tinkoffsimplenews.app.App
import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity
import io.reactivex.Maybe
import javax.inject.Inject


class MainLocalDataSource @Inject constructor(): NewsLocalDataSource {
    // -----------------------------------------------------------------------------------
    // Init
    init {
        App.appComponent.inject(this)
    }

    // -----------------------------------------------------------------------------------
    // Public Fields
    @Inject lateinit var newsDataBase: NewsDataBase

    // -----------------------------------------------------------------------------------
    // Private Fields
    private var isLoadedNewsPreviews = false

    private var loadedNewsPreviews = ArrayList<NewsPreviewEntity>()
    private val loadeNews = HashMap<Long, NewsEntity>()

    // -----------------------------------------------------------------------------------
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

    // -----------------------------------------------------------------------------------
    // Private Fun
    private fun loadNewsPreviews(): Maybe<List<NewsPreviewEntity>> {
        if(isLoadedNewsPreviews) {
            return Maybe.just(loadedNewsPreviews)
        }
        else {
            val newsPreviews = newsDataBase.newsPreviewDao()?.getNewsPreview()

            if (newsPreviews != null) {
                if (newsPreviews.isNotEmpty()) {
                    Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Success")

                    isLoadedNewsPreviews = true
                    loadedNewsPreviews.addAll(newsPreviews)
                    return Maybe.just(newsPreviews)
                }
            }
        }

        Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Empty")
        return Maybe.empty()
    }

    private fun loadNews(newsId: Long): Maybe<NewsEntity> {
        if(loadeNews.containsKey(newsId)){
            return Maybe.just(loadeNews[newsId])
        } else {
            val news = newsDataBase.newsDao()?.getNews(newsId)

            if (news != null) {
                Log.d("LOCAL_DATA_SOURCE", "loadNewsPreviews: Success")

                loadeNews[newsId] = news
                return Maybe.just(news)
            }
        }

        Log.d("LOCAL_DATA_SOURCE", "loadNews: Empty")
        return Maybe.empty()
    }

    private fun saveNewsPreviewsToBd(newsPreviewsEntity: List<NewsPreviewEntity>) {
        isLoadedNewsPreviews = true
        loadedNewsPreviews = ArrayList(newsPreviewsEntity)

        newsDataBase.newsPreviewDao()?.saveNewsPreviews(newsPreviewsEntity)
    }

    private fun saveNewsToBd(news: NewsEntity) {
        loadeNews[news.id] = news
        newsDataBase.newsDao()?.saveNews(news)
    }
}