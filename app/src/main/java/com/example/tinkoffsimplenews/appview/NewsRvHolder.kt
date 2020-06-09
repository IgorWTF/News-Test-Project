package com.example.tinkoffsimplenews.appview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_news.view.*
import com.example.tinkoffsimplenews.datamodel.NewsPreview
import java.text.SimpleDateFormat
import java.util.*

class NewsRvHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val newsLayout = itemView
    private val newsText = itemView.newsText
    private val newsPublicationData = itemView.newsData

    private var id:Long = 0

    fun bind(newsPreviewData: NewsPreview, onNewsClickListener: OnNewsClickListener){
        id = newsPreviewData.id

        newsText.text = newsPreviewData.text
        newsPublicationData.text =
            SimpleDateFormat("dd.MM.yy").format(Date(newsPreviewData.publicationDate))

        newsLayout.setOnClickListener{onNewsClickListener.onClick(id) }
    }
}