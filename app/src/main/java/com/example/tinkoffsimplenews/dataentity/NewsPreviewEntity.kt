package com.example.tinkoffsimplenews.dataentity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NewsPreviewEntity(
    @PrimaryKey
    var id: Long = 0,
    var name: String = "",
    var text: String = "",
    var publicationDate: Long = 0,
    var bankInfoTypeId: Int = 0
)