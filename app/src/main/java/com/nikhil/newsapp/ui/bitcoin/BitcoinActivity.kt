package com.nikhil.newsapp.ui.bitcoin

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.newsapp.R
import com.nikhil.newsapp.adapters.NewsAdapter
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.databinding.ActivityBitcoinNewsBinding
import com.nikhil.newsapp.models.Article
import com.nikhil.newsapp.utils.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class BitcoinActivity : BaseActivity<ActivityBitcoinNewsBinding, BitcoinViewModel>() {

    companion object {
        const val SEARCH_TERM = "bitcoin"
        const val SORT_BY = "publishedAt"
    }

    private var newsAdapter: NewsAdapter? = null
    private var bitcoinNewsArticles: List<Article>? = null

    override val viewModel: BitcoinViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_bitcoin_news

    override fun initViewModel(viewModel: BitcoinViewModel) {
        binding.viewModel = viewModel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = this.getString(R.string.bitcoin_news)
        setupRecyclerView()

        viewModel.getCurrentDate()
        viewModel.currentDate.observe(this, Observer { currentDate ->
            viewModel.getBitcoinNews(
                NewsParams(
                    SEARCH_TERM,
                    currentDate,
                    SORT_BY
                )
            )
        })

        viewModel.newsResponse.observe(this, Observer {
            newsAdapter?.differ?.submitList(it.articles)
            bitcoinNewsArticles = it.articles
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBitcoinNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@BitcoinActivity)
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.default_padding).toInt()
                )
            )
        }
    }

}