package com.example.tinkoffsimplenews.datamodel

data class NewsPreview (
    val id: Long = 0,
    val name: String = "",
    val text: String = "",
    val publicationDate: Long = 0,
    val bankInfoTypeId: Int = 0
)