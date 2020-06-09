package com.example.tinkoffsimplenews.dataentity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NewsEntity (
    @PrimaryKey
    var id: Long = 0,
    var name: String = "",
    var text: String= "",
    var publicationDate: Long = 0,
    var creationDate: Long = 0,
    var lastModificationDate : Long = 0,
    var content: String = "",
    var bankInfoTypeId:Int = 0,
    var typeId: String = ""
)