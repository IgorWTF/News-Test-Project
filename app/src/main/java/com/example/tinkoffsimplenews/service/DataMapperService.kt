package com.example.tinkoffsimplenews.service

import androidx.core.text.HtmlCompat
import com.example.tinkoffsimplenews.dataentity.NewsEntity
import com.example.tinkoffsimplenews.dataentity.NewsPreviewEntity
import com.example.tinkoffsimplenews.datamodel.News
import com.example.tinkoffsimplenews.datamodel.NewsPreview
import com.example.tinkoffsimplenews.datapojo.NewsPOJO
import com.example.tinkoffsimplenews.datapojo.NewsPreviewPOJO

object DataMapperService {
    // -----------------------------------------------------------------------------------
    // News.
    fun mapNewsPojoToModel(newsPojo: NewsPOJO): News {
        return News(
            newsPojo.title.id.toLong(),
            newsPojo.title.name,
            HtmlCompat.fromHtml(
                newsPojo.title.text,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            ).toString(),
            newsPojo.title.publicationDate.milliseconds.toLong(),
            newsPojo.creationDate.milliseconds.toLong(),
            newsPojo.lastModificationDate.milliseconds.toLong(),
            HtmlCompat.fromHtml(
                newsPojo.content,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            ).toString(),
            newsPojo.bankInfoTypeId,
            newsPojo.typeId
        )
    }
    fun mapNewsEntityToModel(newsEntity: NewsEntity): News {
        return News(
            newsEntity.id,
            newsEntity.name,
            newsEntity.text,
            newsEntity.publicationDate,
            newsEntity.creationDate,
            newsEntity.lastModificationDate,
            newsEntity.content,
            newsEntity.bankInfoTypeId,
            newsEntity.typeId
        )
    }
    fun mapNewsModelToEntity(news: News): NewsEntity {
        return NewsEntity(
            news.id,
            news.name,
            news.text,
            news.publicationDate,
            news.creationDate,
            news.lastModificationDate,
            news.content,
            news.bankInfoTypeId,
            news.typeId
        )
    }

    // -----------------------------------------------------------------------------------
    // News Collections.
    fun mapNewsPojoToModel(newsPojo: List<NewsPOJO>): List<News> {
        val news = ArrayList<News>()
        newsPojo.forEach {
            news.add(
                mapNewsPojoToModel(it)
            )
        }
        return news
    }
    fun mapNewsEntityToModel(newsEntity: List<NewsEntity>): List<News> {
        val news = ArrayList<News>()
        newsEntity.forEach {
            news.add(
                mapNewsEntityToModel(it)
            )
        }
        return news
    }
    fun mapNewsModelToEntity(news: List<News>): List<NewsEntity> {
        val newsEntity = ArrayList<NewsEntity>()
        news.forEach {
            newsEntity.add(
                mapNewsModelToEntity(it)
            )
        }
        return newsEntity
    }

    // -----------------------------------------------------------------------------------
    // News Previews.
    fun mapNewsPreviewPojoToModel(newsPreviewPojo: NewsPreviewPOJO): NewsPreview {
        return NewsPreview(
            newsPreviewPojo.id.toLong(),
            newsPreviewPojo.name,
            HtmlCompat.fromHtml(newsPreviewPojo.text, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
            newsPreviewPojo.publicationDate.milliseconds.toLong(),
            newsPreviewPojo.bankInfoTypeId
        )
    }
    fun mapNewsPreviewEntityToModel(newsPreviewEntity: NewsPreviewEntity): NewsPreview {
        return NewsPreview(
            newsPreviewEntity.id,
            newsPreviewEntity.name,
            newsPreviewEntity.text,
            newsPreviewEntity.publicationDate,
            newsPreviewEntity.bankInfoTypeId
        )
    }
    fun mapNewsPreviewModelToEntity(newsPreviews: NewsPreview): NewsPreviewEntity {
        return NewsPreviewEntity(
            newsPreviews.id,
            newsPreviews.name,
            newsPreviews.text,
            newsPreviews.publicationDate,
            newsPreviews.bankInfoTypeId
        )
    }

    // -----------------------------------------------------------------------------------
    // News Previews Collections.
    fun mapNewsPreviewPojoToModel(newsPreviewsPojo: List<NewsPreviewPOJO>): List<NewsPreview> {
        val newsPreviews = ArrayList<NewsPreview>()
        newsPreviewsPojo.forEach {
            newsPreviews.add(
                mapNewsPreviewPojoToModel(it)
            )
        }
        return newsPreviews
    }
    fun mapNewsPreviewEntityToModel(newsPreviewsEntity: List<NewsPreviewEntity>): List<NewsPreview> {
        val newsPreviews = ArrayList<NewsPreview>()
        newsPreviewsEntity.forEach {
            newsPreviews.add(
                mapNewsPreviewEntityToModel(it)
            )
        }
        return newsPreviews
    }
    fun mapNewsPreviewModelToEntity(newsPreviews: List<NewsPreview>): List<NewsPreviewEntity> {
        val newsPreviewsEntity = ArrayList<NewsPreviewEntity>()
        newsPreviews.forEach {
            newsPreviewsEntity.add(
                mapNewsPreviewModelToEntity(it)
            )
        }
        return newsPreviewsEntity
    }
}