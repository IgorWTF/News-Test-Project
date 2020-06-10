package com.example.tinkoffsimplenews.MainRepositoryTests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tinkoffsimplenews.appmodel.MainRepository
import com.example.tinkoffsimplenews.appmodel.localDataSource.NewsLocalDataSource
import com.example.tinkoffsimplenews.appmodel.remoteDataSource.NewsRemoteDataSource
import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity
import com.example.tinkoffsimplenews.datapojo.NewsPOJO
import com.example.tinkoffsimplenews.datapojo.NewsPreviewPOJO
import com.example.tinkoffsimplenews.service.NetService
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainRepositoryTests {
    @Test
    fun getNewsPreviewsIfLocalDSourceEmptyThenRemoteDataSource() {
        val fakeLDataSource = object : NewsLocalDataSource {

            var hasNewsPreviewsRequest = false

            override fun getNewsPreviews(): Maybe<List<NewsPreviewEntity>> {
                hasNewsPreviewsRequest = true
                return Maybe.empty()
            }

            override fun getNews(newsId: Long): Maybe<NewsEntity> {
                return Maybe.empty()
            }

            override fun saveNewsPreviews(newsPreviewsEntity: List<NewsPreviewEntity>) {
                TODO("Not yet implemented")
            }

            override fun saveNews(news: NewsEntity) {
                TODO("Not yet implemented")
            }

        }

        val fakeRDataSource = object : NewsRemoteDataSource {

            var hasNewsPreviewsRequested = false

            override fun getNewsPreviews(): Maybe<List<NewsPreviewPOJO>> {
                hasNewsPreviewsRequested = true
                return Maybe.empty()
            }

            override fun getNews(newsId: Long): Maybe<NewsPOJO> {
                TODO("Not yet implemented")
            }

        }

        val fakeNetService = object :NetService {
            override fun hasNetwork(): Boolean {
                return true
            }
        }

        val mainRepository = MainRepository()

        mainRepository.apply {
            netService = fakeNetService
            newsLocalDataSource = fakeLDataSource
            newsRemoteDataSource = fakeRDataSource
        }

        mainRepository.getNewsPreviews()

        Assert.assertTrue(fakeLDataSource.hasNewsPreviewsRequest &&
                            fakeRDataSource.hasNewsPreviewsRequested)
    }

    @Test
    fun getNewsIfLocalDSourceEmptyThenRemoteDataSource() {
        val fakeLDataSource = object : NewsLocalDataSource {

            var hasNewsRequest = false

            override fun getNewsPreviews(): Maybe<List<NewsPreviewEntity>> {
                return Maybe.empty()
            }
            override fun getNews(newsId: Long): Maybe<NewsEntity> {
                hasNewsRequest = true
                return Maybe.empty()
            }
            override fun saveNewsPreviews(newsPreviewsEntity: List<NewsPreviewEntity>) {
                TODO("Not yet implemented")
            }
            override fun saveNews(news: NewsEntity) {
                TODO("Not yet implemented")
            }

        }

        val fakeRDataSource = object : NewsRemoteDataSource {

            var hasNewsRequested = false
            override fun getNewsPreviews(): Maybe<List<NewsPreviewPOJO>> {
                return Maybe.empty()
            }
            override fun getNews(newsId: Long): Maybe<NewsPOJO> {
                hasNewsRequested = true
                return Maybe.empty()
            }

        }

        val fakeNetService = object :NetService {
            override fun hasNetwork(): Boolean {
                return true
            }
        }

        val mainRepository = MainRepository()

        mainRepository.apply {
            netService = fakeNetService
            newsLocalDataSource = fakeLDataSource
            newsRemoteDataSource = fakeRDataSource
        }

        mainRepository.getNews(123)

        Assert.assertTrue(fakeLDataSource.hasNewsRequest &&
                            fakeRDataSource.hasNewsRequested)
    }
}