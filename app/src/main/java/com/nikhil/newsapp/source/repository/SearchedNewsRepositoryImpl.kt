package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.SearchedNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.SearchedNewsRemoteDataSource
import com.nikhil.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchedNewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchedNewsRemoteDataSource
) : SearchedNewsRepository {

    override suspend fun getSearchedNews(searchedNewsParams: SearchedNewsParams): Result<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext remoteDataSource.getSearchedNews(searchedNewsParams)
        }
}