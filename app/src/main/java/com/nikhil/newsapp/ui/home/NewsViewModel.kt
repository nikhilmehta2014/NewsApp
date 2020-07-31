package com.nikhil.newsapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhil.newsapp.base.BaseViewModel
import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.source.repository.NewsRepository
import com.nikhil.newsapp.utils.Result
import com.nikhil.newsapp.utils.asLiveData
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    private val repository: NewsRepository
) : BaseViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    fun getAllNews(allNewsParams: AllNewsParams) {
        _isLoading.value = false
        viewModelScope.launch {
            when (val result = repository.getAllNews(allNewsParams)) {
                is Result.Success -> {
                    println("NewsVM = Success")
                    println("result = $result")
                }
                is Result.Error -> {
                    println("NewsVM = Error")
                }
            }
        }
    }


}