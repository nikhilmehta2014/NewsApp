package com.nikhil.newsapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.databinding.ActivityNewsBinding
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import com.nikhil.newsapp.utils.MarginItemDecoration
import com.nikhil.newsapp.utils.NetworkUtil
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

        binding.shimmerLayout.startShimmer()

        //TODO: Is this the right way to pass data by hardcoding here?
        //Because of the free News API account, the limit is to search for last 30 days only
        if (NetworkUtil.isNetworkAvailable(this)) {
            viewModel.getAllNews(AllNewsParams("bitcoin", "2020-07-10", "publishedAt"))
        } else {
            Toast.makeText(this, "No Internet Available", Toast.LENGTH_SHORT).show()
        }

        viewModel.allNewsArticles.observe(
            this,
            Observer {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                allNewsArticles = it
            }
        )

        viewModel.shouldShowAllNews.observe(
            this,
            Observer { shouldShowAllNews ->
                if (shouldShowAllNews) {
                    allNewsAdapter = AllNewsAdapter(allNewsArticles) { url ->
                        onNewsItemClicked(url)
                    }
                    binding.rvAllNews.apply {
                        layoutManager = LinearLayoutManager(this@AllNewsActivity)
                        adapter = allNewsAdapter
                        addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_padding).toInt()))
                    }
                }
            }
        )
    }

    private fun onNewsItemClicked(url: String) {
        Toast.makeText(this, "url = $url", Toast.LENGTH_SHORT).show()
    }
}