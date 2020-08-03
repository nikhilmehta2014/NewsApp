package com.nikhil.newsapp.ui.technews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.newsapp.R
import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.databinding.FragmentTechNewsBinding
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import com.nikhil.newsapp.ui.home.NewsActivity
import com.nikhil.newsapp.ui.home.NewsViewModel
import com.nikhil.newsapp.utils.MarginItemDecoration
import com.nikhil.newsapp.utils.NetworkUtil

class TechNewsFragment : Fragment() {

    private lateinit var binding: FragmentTechNewsBinding
    private lateinit var viewModel: NewsViewModel
    private var allNewsArticles: List<GetNewsResponseEntity.Article>? = null
    private var techNewsAdapter: TechNewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tech_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

        binding.shimmerLayout.startShimmer()

        //Because of the free News API account, the limit is to search for last 30 days only
        if (NetworkUtil.isNetworkAvailable(activity as NewsActivity)) {
            viewModel.getAllNews(
                AllNewsParams(
                    NewsActivity.SEARCH_TERM,
                    NewsActivity.FROM_DATE,
                    NewsActivity.SORT_TYPE
                )
            )
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