package com.nikhil.newsapp.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.databinding.ActivityNewsBinding
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllNewsActivity : BaseActivity<ActivityNewsBinding, AllNewsViewModel>() {

    private var allNewsAdapter: AllNewsAdapter? = null
    private var allNewsArticles: List<GetNewsResponseEntity.Article>? = null

    override val viewModel: AllNewsViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_news

    override fun initViewModel(viewModel: AllNewsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: Is this the right way to pass data by hardcoding here?
        viewModel.getAllNews(AllNewsParams("bitcoin", "2020-06-30", "publishedAt"))

        viewModel.allNewsArticles.observe(
            this,
            Observer {
                allNewsArticles = it
            }
        )

        viewModel.shouldShowAllNews.observe(
            this,
            Observer { shouldShowAllNews ->
                if (shouldShowAllNews) {
                    allNewsAdapter = AllNewsAdapter(allNewsArticles)
                }
            }
        )
    }
}