package com.nikhil.newsapp.ui.home

import androidx.activity.viewModels
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.databinding.ActivityNewsBinding

class NewsActivity : BaseActivity<ActivityNewsBinding, NewsViewModel>() {

    override val viewModel: NewsViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_news

    override fun initViewModel(viewModel: NewsViewModel) {
        binding.viewModel = viewModel
    }
}