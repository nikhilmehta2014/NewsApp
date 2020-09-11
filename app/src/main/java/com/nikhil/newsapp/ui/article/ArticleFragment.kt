package com.nikhil.newsapp.ui.article

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseFragment
import com.nikhil.newsapp.databinding.FragmentArticleBinding
import com.nikhil.newsapp.ui.TechNewsActivity
import com.nikhil.newsapp.ui.TechNewsViewModel

class ArticleFragment : BaseFragment<FragmentArticleBinding, TechNewsViewModel>() {

    override fun getLayoutRes() = R.layout.fragment_article

    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as TechNewsActivity).viewModel
        val article = args.article

        binding.webview.apply {
            webViewClient = WebViewClient()
            article.url?.let {
                loadUrl(it)
            }
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }

}