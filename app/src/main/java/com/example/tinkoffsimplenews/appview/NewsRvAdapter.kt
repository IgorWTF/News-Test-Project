package com.example.tinkoffsimplenews.appview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tinkoffsimplenews.R
import com.example.tinkoffsimplenews.datamodel.NewsPreview

// Simple implementation of an adapter
// to work with NewsRvHolder.
class NewsRvAdapter(private val context: Context,
                    private val newsPreviews: ArrayList<NewsPreview>,
                    private val onNewsClickListener: OnNewsClickListener):
    RecyclerView.Adapter<NewsRvHolder>() {

    // -----------------------------------------------------------------------------------
    // Override

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsRvHolder {
        val layoutInflater = LayoutInflater.from(context)
        val newsViewHolder = layoutInflater.inflate(R.layout.rv_item_news, parent, false)
        return NewsRvHolder(newsViewHolder)
    }

    override fun onBindViewHolder(holder: NewsRvHolder, position: Int) {
        holder.bind(newsPreviews[position], onNewsClickListener)
    }

    override fun getItemCount(): Int {
        return  newsPreviews.size
    }
}