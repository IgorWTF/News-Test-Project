package com.example.tinkoffsimplenews.appmodel.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity

@Database(entities = [NewsEntity::class, NewsPreviewEntity::class], version = 1)
abstract class MainDataBase : RoomDatabase(), NewsDataBase {
    abstract override fun newsDao(): NewsDao?
    abstract override fun newsPreviewDao(): NewsPreviewDao?
}