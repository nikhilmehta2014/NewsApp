package com.nikhil.newsapp.ui.article

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseFragment
import com.nikhil.newsapp.databinding.FragmentArticleBinding
import com.nikhil.newsapp.ui.NewsActivity
import com.nikhil.newsapp.ui.NewsViewModel

class ArticleFragment : BaseFragment<FragmentArticleBinding, NewsViewModel>() {

    override fun getLayoutRes() = R.layout.fragment_article

    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        val article = args.article

        binding.webview.apply {
            webViewClient = WebViewClient()
            article.url?.let {
                loadUrl(it)
            }
        }

    }

}