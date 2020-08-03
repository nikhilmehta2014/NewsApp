package com.nikhil.newsapp.ui.searchnews

import androidx.fragment.app.Fragment
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseFragment
import com.nikhil.newsapp.databinding.FragmentSearchNewsBinding
import com.nikhil.newsapp.ui.home.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchNewsFragment : BaseFragment<FragmentSearchNewsBinding, NewsViewModel>() {

    override fun getLayoutRes() = R.layout.fragment_search_news

}