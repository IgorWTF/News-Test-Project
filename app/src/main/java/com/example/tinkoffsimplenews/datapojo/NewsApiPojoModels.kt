package com.example.tinkoffsimplenews.datapojo

import com.google.gson.annotations.SerializedName

// NewsPreviews
data class NewsPreviewsResultPOJO(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("payload")
    val newsPreview: ArrayList<NewsPreviewPOJO>,
    @SerializedName("trackingId")
    val trackingId:String,
    @SerializedName("details")
    val detailsPOJO: DetailsPOJO
)
data class NewsPreviewPOJO(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("text")
    val text:String,
    @SerializedName("publicationDate")
    val publicationDate: TimePOJO,
    @SerializedName("bankInfoTypeId")
    val bankInfoTypeId: Int
)
data class DetailsPOJO (
    @SerializedName("hasNext")
    val hasNext : Boolean
)

// News
data class NewsResultPOJO (
    @SerializedName("resultCode")
    val resultCode : String,
    @SerializedName("payload")
    val news : NewsPOJO,
    @SerializedName("trackingId")
    val trackingId : String
)
data class NewsTitlePOJO (
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("text")
    val text : String,
    @SerializedName("publicationDate")
    val publicationDate : TimePOJO,
    @SerializedName("bankInfoTypeId")
    val bankInfoTypeId : Int
)
data class NewsPOJO (
    @SerializedName("title")
    val title : NewsTitlePOJO,
    @SerializedName("creationDate")
    val creationDate : TimePOJO,
    @SerializedName("lastModificationDate")
    val lastModificationDate : TimePOJO,
    @SerializedName("content")
    val content : String,
    @SerializedName("bankInfoTypeId")
    val bankInfoTypeId : Int,
    @SerializedName("typeId")
    val typeId : String
)

// Time
data class TimePOJO(
    val milliseconds : String
)
