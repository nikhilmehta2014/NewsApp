package com.nikhil.newsapp.ui.searchnews

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.newsapp.R
import com.nikhil.newsapp.adapters.NewsAdapter
import com.nikhil.newsapp.base.BaseFragment
import com.nikhil.newsapp.data.SearchedNewsParams
import com.nikhil.newsapp.databinding.FragmentSearchNewsBinding
import com.nikhil.newsapp.models.Article
import com.nikhil.newsapp.ui.NewsActivity
import com.nikhil.newsapp.ui.NewsViewModel
import com.nikhil.newsapp.utils.Constants.SEARCH_NEWS_TIME_DELAY
import com.nikhil.newsapp.utils.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchNewsFragment : BaseFragment<FragmentSearchNewsBinding, NewsViewModel>() {

    companion object {
        const val PAGE = 1
    }

    private var SearchNewsArticles: List<Article>? = null
    private var newsAdapter: NewsAdapter? = null

    override fun getLayoutRes() = R.layout.fragment_search_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter?.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }

        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.getSearchedNews(SearchedNewsParams(editable.toString(), PAGE))
                    }
                }
            }
        }

        viewModel.searchedNewsArticles.observe(
            viewLifecycleOwner,
            Observer {
                newsAdapter?.differ?.submitList(it.toList())
                SearchNewsArticles = it
            }
        )
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.default_padding).toInt()
                )
            )
        }
    }
}