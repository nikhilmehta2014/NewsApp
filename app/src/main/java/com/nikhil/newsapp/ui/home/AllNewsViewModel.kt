package com.nikhil.newsapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhil.newsapp.base.BaseViewModel
import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import com.nikhil.newsapp.source.repository.AllNewsRepository
import com.nikhil.newsapp.utils.Result
import com.nikhil.newsapp.utils.asLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

class AllNewsViewModel @ViewModelInject constructor(
    private val repository: AllNewsRepository
) : BaseViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _shouldShowAllNews = MutableLiveData<Boolean>()
    val shouldShowAllNews = _shouldShowAllNews.asLiveData()

    private val _allNewsArticles = MutableLiveData<List<GetNewsResponseEntity.Article>>()
    val allNewsArticles = _allNewsArticles.asLiveData()

    fun getAllNews(allNewsParams: AllNewsParams) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getAllNews(allNewsParams)) {
                is Result.Success -> {
                    Timber.d("NewsVM = Success")
                    _isLoading.value = false
                    _allNewsArticles.value = result.data?.articles
                    _shouldShowAllNews.value=true
                }
                is Result.Error -> {
                    println("NewsVM = Error")
                }
            }
        }
    }


}