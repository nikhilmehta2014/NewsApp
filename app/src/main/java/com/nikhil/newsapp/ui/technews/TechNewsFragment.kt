package com.nikhil.newsapp.ui.technews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.newsapp.R
import com.nikhil.newsapp.adapters.NewsAdapter
import com.nikhil.newsapp.base.BaseFragment
import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.databinding.FragmentTechNewsBinding
import com.nikhil.newsapp.models.Article
import com.nikhil.newsapp.ui.NewsActivity
import com.nikhil.newsapp.ui.NewsViewModel
import com.nikhil.newsapp.utils.MarginItemDecoration
import com.nikhil.newsapp.utils.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechNewsFragment : BaseFragment<FragmentTechNewsBinding, NewsViewModel>() {

    companion object {
        const val COUNTRY = "us"
        const val CATEGORY = "technology"
    }

    private var allNewsArticles: List<Article>? = null
    private var newsAdapter: NewsAdapter? = null

    override fun getLayoutRes() = R.layout.fragment_tech_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter?.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_techNewsFragment_to_articleFragment,
                bundle
            )
        }

        binding.shimmerLayout.startShimmer()

        if (NetworkUtil.isNetworkAvailable(activity as NewsActivity)) {
            //Because of the free News API account, the limit is to search for last 30 days only
//            viewModel.getAllNews(AllNewsParams(SEARCH_TERM, FROM_DATE, SORT_TYPE))
            viewModel.getTechNews(TechNewsParams(COUNTRY, CATEGORY))
        } else {
            Toast.makeText(activity, "No Internet Available", Toast.LENGTH_SHORT).show()
        }

        viewModel.allNewsArticles.observe(
            viewLifecycleOwner,
            Observer {
                newsAdapter?.differ?.submitList(it.toList())
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                allNewsArticles = it
            }
        )
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvAllNews.apply {
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