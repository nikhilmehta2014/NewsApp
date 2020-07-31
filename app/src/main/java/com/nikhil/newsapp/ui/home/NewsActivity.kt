package com.nikhil.newsapp.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsBinding, NewsViewModel>() {

    override val viewModel: NewsViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_news

    override fun initViewModel(viewModel: NewsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: Is this the right way to pass data by hardcoding here?
        viewModel.getAllNews(AllNewsParams("bitcoin", "2020-06-30", "publishedAt"))
    }
}