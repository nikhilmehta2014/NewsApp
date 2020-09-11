package com.nikhil.newsapp.ui.bitcoin

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhil.newsapp.base.BaseViewModel
import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.repository.NewsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BitcoinViewModel @ViewModelInject constructor(
    private val bitcoinNewsRepository: NewsRepository
) : BaseViewModel() {

    private val _newsResponse = MutableLiveData<NewsResponse>()
    val newsResponse: LiveData<NewsResponse>
        get() = _newsResponse

    fun getBitcoinNews(newsParams: NewsParams) {
        viewModelScope.launch {
            bitcoinNewsRepository.getAllNews(newsParams)
                .onStart {

                }
                .catch {

                }
                .collect {
                    _newsResponse.value = it
                }
        }
    }
}