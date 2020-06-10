package com.example.tinkoffsimplenews.appviewmodel

import androidx.lifecycle.MutableLiveData
import com.example.tinkoffsimplenews.datamodel.News
import com.example.tinkoffsimplenews.datamodel.NewsPreview

interface NewsViewModel {
    var newsPreviewsDataState:MutableLiveData<DataLoadState>
    var newsPreviewsData: ArrayList<NewsPreview>

    var newsDataState: MutableLiveData<DataLoadState>
    var newsData:News

    fun getNewsPreviews()
    fun getNews(newsId: Long)

    fun updateNewsPreviews()
    fun updateNews(newsId: Long)
}