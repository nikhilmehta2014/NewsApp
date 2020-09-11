package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.NewsRemoteDataSource
import com.nikhil.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getAllNews(newsParams: NewsParams): Result<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext remoteDataSource.getAllNews(newsParams)
        }
}