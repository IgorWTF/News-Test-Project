package com.example.tinkoffsimplenews.datamodel

data class News (
    val id: Long = 0,
    val name: String = "",
    val text: String= "",
    val publicationDate: Long = 0,
    val creationDate: Long = 0,
    val lastModificationDate : Long = 0,
    val content: String = "",
    val bankInfoTypeId:Int = 0,
    val typeId: String = ""
)