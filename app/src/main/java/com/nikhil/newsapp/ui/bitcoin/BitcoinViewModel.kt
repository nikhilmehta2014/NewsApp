package com.nikhil.newsapp.ui.bitcoin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhil.newsapp.base.BaseViewModel
import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BitcoinViewModel @Inject constructor(
    private val bitcoinNewsRepository: NewsRepository
) : BaseViewModel() {

    private val _newsResponse = MutableLiveData<NewsResponse>()
    val newsResponse: LiveData<NewsResponse>
        get() = _newsResponse

    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String>
        get() = _currentDate

    fun getCurrentDate() {
        viewModelScope.launch {
            bitcoinNewsRepository.getCurrentDate()
                .collect {
                    _currentDate.value = it
                }
        }
    }


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