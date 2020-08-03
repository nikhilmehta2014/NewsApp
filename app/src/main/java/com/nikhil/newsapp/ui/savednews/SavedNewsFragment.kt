package com.nikhil.newsapp.ui.savednews

import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseFragment
import com.nikhil.newsapp.databinding.FragmentSavedNewsBinding
import com.nikhil.newsapp.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : BaseFragment<FragmentSavedNewsBinding, NewsViewModel>() {

    override fun getLayoutRes() = R.layout.fragment_saved_news

}