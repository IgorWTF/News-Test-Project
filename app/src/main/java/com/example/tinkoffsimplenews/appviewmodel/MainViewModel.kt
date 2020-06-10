package com.example.tinkoffsimplenews.appviewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tinkoffsimplenews.app.App
import com.example.tinkoffsimplenews.appmodel.MainRepository
import com.example.tinkoffsimplenews.appmodel.NewsRepository
import com.example.tinkoffsimplenews.datamodel.News
import com.example.tinkoffsimplenews.datamodel.NewsPreview
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainViewModel(application: Application): AndroidViewModel(application), NewsViewModel {

    // Init
    init {
        App.appComponent.inject(this)
    }

    // Public Fields
    @Inject lateinit var newsRepository: NewsRepository

    // Private Fields
    private val compositeDisposable = CompositeDisposable()

    // NewsViewModel implementation
    override var newsPreviewsDataState = MutableLiveData<DataLoadState>(DataLoadState.NotLoaded)
    override var newsPreviewsData = ArrayList<NewsPreview>()

    override var newsDataState = MutableLiveData<DataLoadState>(DataLoadState.NotLoaded)
    override var newsData = News()

    // Public Fun
    override fun getNewsPreviews() {
        newsPreviewsDataState.value = DataLoadState.Loading

        val repositoryDataHandler = object : DisposableMaybeObserver<List<NewsPreview>>() {
            override fun onSuccess(t: List<NewsPreview>) {
                Log.d("MAIN_VIEW_MODEL", "getNewsPreviews: Success")
                newsPreviewsData = ArrayList(t)
                newsPreviewsDataState.value = DataLoadState.Loaded
            }
            override fun onComplete() {
                Log.d("MAIN_VIEW_MODEL", "getNewsPreviews: Complete")
                newsPreviewsDataState.value = DataLoadState.Error
            }
            override fun onError(e: Throwable) {
                newsPreviewsDataState.value = DataLoadState.Error
                Log.d("MAIN_VIEW_MODEL", "getNewsPreviews: Error")
                Log.d("MAIN_VIEW_MODEL", e.message.toString())
            }
        }

        compositeDisposable.add(
            newsRepository.getNewsPreviews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(repositoryDataHandler)
        )
    }
    override fun getNews(newsId: Long) {
        newsDataState.value = DataLoadState.Loading

        val repositoryDataHandler = object : DisposableMaybeObserver<News>() {
            override fun onSuccess(t: News) {
                Log.d("MAIN_VIEW_MODEL", "getNews: Success")
                newsData = t
                newsDataState.value = DataLoadState.Loaded
            }
            override fun onComplete() {
                Log.d("MAIN_VIEW_MODEL", "getNews: Complete")
                newsDataState.value = DataLoadState.Error
            }
            override fun onError(e: Throwable) {
                Log.d("MAIN_VIEW_MODEL", "getNews: Error")
                newsDataState.value = DataLoadState.Error
                Log.d("MAIN_VIEW_MODEL", e.message.toString())
            }
        }

        compositeDisposable.add(
            newsRepository.getNews(newsId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(repositoryDataHandler)
        )
    }

    override fun updateNewsPreviews() {
        newsPreviewsDataState.value = DataLoadState.Loading

        val repositoryDataHandler = object : DisposableMaybeObserver<List<NewsPreview>>() {
            override fun onSuccess(t: List<NewsPreview>) {
                Log.d("MAIN_VIEW_MODEL", "updateNewsPreviews: Success")
                newsPreviewsData = ArrayList(t)
                newsPreviewsDataState.value = DataLoadState.Loaded
            }
            override fun onComplete() {
                Log.d("MAIN_VIEW_MODEL", "updateNewsPreviews: Complete")
                newsPreviewsDataState.value = DataLoadState.Error
            }
            override fun onError(e: Throwable) {
                newsPreviewsDataState.value = DataLoadState.Error
                Log.d("MAIN_VIEW_MODEL", "updateNewsPreviews: Error")
                Log.d("MAIN_VIEW_MODEL", e.message.toString())
            }
        }

        compositeDisposable.add(
            newsRepository.updateNewsPreviews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(repositoryDataHandler)
        )
    }
    override fun updateNews(newsId: Long) {
        newsDataState.value = DataLoadState.Loading

        val repositoryDataHandler = object : DisposableMaybeObserver<News>() {
            override fun onSuccess(t: News) {
                Log.d("MAIN_VIEW_MODEL", "updateNews: Success")
                newsData = t
                newsDataState.value = DataLoadState.Loaded
            }
            override fun onComplete() {
                Log.d("MAIN_VIEW_MODEL", "updateNews: Complete")
                newsDataState.value = DataLoadState.Error
            }
            override fun onError(e: Throwable) {
                Log.d("MAIN_VIEW_MODEL", "updateNews: Error")
                Log.d("MAIN_VIEW_MODEL", e.message.toString())
                newsDataState.value = DataLoadState.Error
            }
        }

        compositeDisposable.add(
            newsRepository.updateNews(newsId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(repositoryDataHandler)
        )
    }

    // Override
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}