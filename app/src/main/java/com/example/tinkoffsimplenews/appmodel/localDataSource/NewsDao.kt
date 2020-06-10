package com.example.tinkoffsimplenews.appmodel.localDataSource

import androidx.room.*
import com.example.tinkoffsimplenews.dataentity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM NewsEntity WHERE id = :newsId")
    fun getNews(newsId: Long) : NewsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNews(news: NewsEntity)
}