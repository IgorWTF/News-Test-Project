package com.example.tinkoffsimplenews.appview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tinkoffsimplenews.R
import com.example.tinkoffsimplenews.appviewmodel.DataLoadState
import com.example.tinkoffsimplenews.appviewmodel.MainViewModel
import com.example.tinkoffsimplenews.datamodel.News
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.view.*
import java.text.SimpleDateFormat
import java.util.*


class SecondActivity : AppCompatActivity() {

    companion object{
        const val NEWS_ID = "news_id"
    }

    private var newsId: Long = 0
    private lateinit var newsTitle: TextView
    private lateinit var newsContent: TextView
    private lateinit var newsDate: TextView

    private lateinit var contentScrollView:ScrollView
    private lateinit var loadingLayout:FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        newsId = intent.getLongExtra(NEWS_ID, 0)

        setupUI()
        setupMainViewModel()
        setupSwipeRefreshLayout()

        onDataStatusChange()
    }

    private fun setupUI() {
        newsTitle = constraintLayout.newsTitle
        newsContent = constraintLayout.newsContent
        newsDate = constraintLayout.newsDate

        contentScrollView = constraintLayout.scrollView
        loadingLayout = constraintLayout.loadingLayout
        swipeRefreshLayout = constraintLayout.swipeRefreshLayout
    }
    private fun setupSwipeRefreshLayout() {
        val swipeRefreshHandler = SwipeRefreshLayout.OnRefreshListener { updateNews(newsId) }
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshHandler)
    }
    private fun setupMainViewModel() {
        val liveDataHandler = Observer<DataLoadState> { onDataStatusChange() }
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.newsDataState.observe(this, liveDataHandler)
    }

    private fun loadNews(newsId: Long) {
        mainViewModel.getNews(newsId)
    }
    private fun updateNews(newsId: Long){
        mainViewModel.updateNews(newsId)
    }

    private fun onDataStatusChange() {
        val dataState = mainViewModel.newsDataState.value

        loadingLayout.visibility =
            if (dataState == DataLoadState.NotLoaded || dataState == DataLoadState.Error)
                View.VISIBLE
            else
                View.INVISIBLE

        contentScrollView.visibility =
            if (dataState == DataLoadState.Loaded)
                View.VISIBLE
            else
                View.INVISIBLE

        swipeRefreshLayout.isRefreshing = (dataState == DataLoadState.Loading)

        if(dataState == DataLoadState.NotLoaded) {
            loadNews(newsId)
            return
        }

        if (dataState == DataLoadState.Loaded) {
            newsTitle.text = mainViewModel.newsData.text
            newsContent.text = mainViewModel.newsData.content
            newsDate.text = SimpleDateFormat("dd.MM.yy")
                .format(Date(mainViewModel.newsData.creationDate))
        }
    }
}