package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {

    override suspend fun getAllNews(newsParams: NewsParams): Flow<NewsResponse> =
        flow {
            val result = newsApiService.getBitcoinNews(
                newsParams.searchTerm,
                newsParams.fromDate,
                newsParams.sortType,
                Constants.API_KEY
            )
            emit(result)
        }.flowOn(Dispatchers.IO)
}