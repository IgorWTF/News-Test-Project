package com.example.tinkoffsimplenews.appmodel

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity

@Database(entities = [NewsEntity::class, NewsPreviewEntity::class], version = 1)
abstract class CacheDataBase : RoomDatabase() {
    abstract fun NewsDao(): NewsDao?
    abstract fun NewsPreviewDao(): NewsPreviewDao?
}