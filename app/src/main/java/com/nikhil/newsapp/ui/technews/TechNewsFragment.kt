package com.nikhil.newsapp.ui.technews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseFragment
import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.databinding.FragmentTechNewsBinding
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import com.nikhil.newsapp.ui.home.NewsActivity
import com.nikhil.newsapp.ui.home.NewsViewModel
import com.nikhil.newsapp.utils.MarginItemDecoration
import com.nikhil.newsapp.utils.NetworkUtil

class TechNewsFragment : BaseFragment<FragmentTechNewsBinding, NewsViewModel>() {

    companion object {
//        const val SEARCH_TERM = "bitcoin"
//        const val FROM_DATE = "2020-07-10"
//        const val SORT_TYPE = "publishedAt"

        const val COUNTRY = "us"
        const val CATEGORY = "technology"
    }

    private var allNewsArticles: List<GetNewsResponseEntity.Article>? = null
    private var techNewsAdapter: TechNewsAdapter? = null

    override fun getLayoutRes() = R.layout.fragment_tech_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

        binding.shimmerLayout.startShimmer()

        //Because of the free News API account, the limit is to search for last 30 days only
        if (NetworkUtil.isNetworkAvailable(activity as NewsActivity)) {
//            viewModel.getAllNews(AllNewsParams(SEARCH_TERM, FROM_DATE, SORT_TYPE))
            viewModel.getTechNews(TechNewsParams(COUNTRY, CATEGORY))
        } else {
            Toast.makeText(activity, "No Internet Available", Toast.LENGTH_SHORT).show()
        }

        viewModel.allNewsArticles.observe(
            viewLifecycleOwner,
            Observer {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                allNewsArticles = it
            }
        )

        viewModel.shouldShowAllNews.observe(
            viewLifecycleOwner,
            Observer { shouldShowAllNews ->
                if (shouldShowAllNews) {
                    techNewsAdapter =
                        TechNewsAdapter(
                            allNewsArticles
                        ) { url ->
                            onNewsItemClicked(url)
                        }
                    binding.rvAllNews.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = techNewsAdapter
                        addItemDecoration(
                            MarginItemDecoration(
                                resources.getDimension(R.dimen.default_padding).toInt()
                            )
                        )
                    }
                }
            }
        )
    }

    private fun onNewsItemClicked(url: String) {
        Toast.makeText(activity, "url = $url", Toast.LENGTH_SHORT).show()
    }

}