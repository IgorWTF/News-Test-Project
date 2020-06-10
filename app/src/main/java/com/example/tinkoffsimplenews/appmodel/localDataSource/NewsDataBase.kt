package com.example.tinkoffsimplenews.appmodel.localDataSource

interface NewsDataBase {
    fun newsDao(): NewsDao?
    fun newsPreviewDao(): NewsPreviewDao?
}