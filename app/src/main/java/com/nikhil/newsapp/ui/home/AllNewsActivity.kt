package com.nikhil.newsapp.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.databinding.ActivityNewsBinding
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import com.nikhil.newsapp.utils.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        //Because of the free News API account, the limit is to search for last 30 days only
        viewModel.getAllNews(AllNewsParams("bitcoin", "2020-07-10", "publishedAt"))

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
                    binding.rvAllNews.apply {
                        layoutManager = LinearLayoutManager(this@AllNewsActivity)
                        adapter = allNewsAdapter
                        addItemDecoration(
                            MarginItemDecoration(
                                resources.getDimension(R.dimen.default_padding).toInt()
                            )
                        )
                    }
                }
            }
        )
    }
}