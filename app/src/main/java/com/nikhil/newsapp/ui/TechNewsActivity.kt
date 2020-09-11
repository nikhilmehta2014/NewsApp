package com.nikhil.newsapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.databinding.ActivityTechNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_tech_news.*

@AndroidEntryPoint
class TechNewsActivity : BaseActivity<ActivityTechNewsBinding, TechNewsViewModel>() {

    override val viewModel: TechNewsViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_tech_news

    override fun initViewModel(viewModel: TechNewsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = this.getString(R.string.news_tech)

        binding.bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}