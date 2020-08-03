package com.nikhil.newsapp.ui.home

import androidx.activity.viewModels
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsBinding, NewsViewModel>() {

    companion object {
        const val SEARCH_TERM = "bitcoin"
        const val FROM_DATE = "2020-07-10"
        const val SORT_TYPE = "publishedAt"
    }


    override val viewModel: NewsViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_news

    override fun initViewModel(viewModel: NewsViewModel) {
        binding.viewModel = viewModel
    }
}