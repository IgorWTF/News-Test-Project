package com.example.tinkoffsimplenews.appmodel.localDataSource

import androidx.room.*
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity

@Dao
interface NewsPreviewDao {
    @Query("SELECT * FROM NewsPreviewEntity")
    fun getNewsPreview(): List<NewsPreviewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewsPreviews(newsPreview : List<NewsPreviewEntity>)
}