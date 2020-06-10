package com.example.tinkoffsimplenews.appmodel.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity

@Database(entities = [NewsEntity::class, NewsPreviewEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao?
    abstract fun newsPreviewDao(): NewsPreviewDao?
}