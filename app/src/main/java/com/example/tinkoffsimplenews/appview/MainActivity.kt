package com.example.tinkoffsimplenews.appview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tinkoffsimplenews.R
import com.example.tinkoffsimplenews.appviewmodel.DataLoadState
import com.example.tinkoffsimplenews.appviewmodel.MainViewModel
import com.example.tinkoffsimplenews.appviewmodel.NewsViewModel
import com.example.tinkoffsimplenews.datamodel.NewsPreview
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    // -----------------------------------------------------------------------------------
    // Private Fields
    private val newsPreviews = ArrayList<NewsPreview>()

    private lateinit var emptyContentLayout: FrameLayout
    private lateinit var contentLayout: FrameLayout
    private lateinit var updateLayout: FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRvAdapter: NewsRvAdapter

    // -----------------------------------------------------------------------------------
    // AppCompatActivity implementation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupUI()
        setupSwipeRefreshLayout()
        setupMainViewModel()
        setupNewsRecyclerView()

        onDataStatusChange()
    }

    // -----------------------------------------------------------------------------------
    // Private Fun
    private fun setupUI(){
        emptyContentLayout = mainLayout.emptyContentLayout
        contentLayout = mainLayout.contentLayout
        updateLayout = mainLayout.updateLayout
        swipeRefreshLayout = contentLayout.swipeRefreshLayout
    }
    private fun setupSwipeRefreshLayout(){
        val swipeRefreshHandler = SwipeRefreshLayout.OnRefreshListener { updateNewsPreviews() }
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshHandler)
    }
    private fun setupMainViewModel(){
        val liveDataObserver = Observer<DataLoadState> { onDataStatusChange() }
        newsViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        newsViewModel.newsPreviewsDataState.observe(this, liveDataObserver)
    }
    private fun setupNewsRecyclerView() {
        val rvOnItemClickListener = object :OnNewsClickListener{
            override fun onClick(newsId: Long) {
                openNews(newsId)
            }

        }
        newsRvAdapter = NewsRvAdapter(this, newsPreviews, rvOnItemClickListener)

        val newsRecyclerView = contentLayout.swipeRefreshLayout.recyclerView
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.adapter = newsRvAdapter
    }

    private fun loadNewsPreviews() {
        newsViewModel.getNewsPreviews()
    }
    private fun updateNewsPreviews() {
        newsViewModel.updateNewsPreviews()
    }
    private fun openNews(newsId:Long){
        val newActivityIntent = Intent(this, SecondActivity::class.java)
        newActivityIntent.putExtra(SecondActivity.NEWS_ID, newsId)

        startActivity(newActivityIntent)
    }

    private fun onDataStatusChange() {
        val dataState = newsViewModel.newsPreviewsDataState.value
        val data = newsViewModel.newsPreviewsData

        emptyContentLayout.visibility =
            if (data.size == 0) View.VISIBLE
            else View.INVISIBLE

        swipeRefreshLayout.isRefreshing = (dataState == DataLoadState.Loading)

        if(dataState == DataLoadState.NotLoaded) {
            loadNewsPreviews()
            return
        }

        if (dataState == DataLoadState.Loaded) {
            newsPreviews.clear()
            newsPreviews.addAll(data)
            newsRvAdapter.notifyDataSetChanged()
        }
    }
}