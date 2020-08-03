package com.nikhil.newsapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhil.newsapp.base.BaseViewModel
import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.models.Article
import com.nikhil.newsapp.source.repository.TechNewsRepository
import com.nikhil.newsapp.utils.Result
import com.nikhil.newsapp.utils.asLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsViewModel @ViewModelInject constructor(
//    private val repository: AllNewsRepository
    private val repository: TechNewsRepository
) : BaseViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _shouldShowAllNews = MutableLiveData<Boolean>()
    val shouldShowAllNews = _shouldShowAllNews.asLiveData()

    private val _allNewsArticles = MutableLiveData<List<Article>>()
    val allNewsArticles = _allNewsArticles.asLiveData()

    fun getTechNews(techNewsParams: TechNewsParams) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getTechNews(techNewsParams)) {
                is Result.Success -> {
                    Timber.d("NewsVM = Result.Success = ${result.data}")
                    _isLoading.value = false
                    _allNewsArticles.value = result.data?.articles
                    _shouldShowAllNews.value=true
                }
                is Result.Error -> {
                    Timber.d("NewsVM = Error")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("VM onCleared called")
    }


}