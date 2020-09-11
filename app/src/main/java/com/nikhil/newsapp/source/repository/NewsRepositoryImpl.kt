package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getAllNews(newsParams: NewsParams): Flow<NewsResponse> =
        remoteDataSource.getAllNews(newsParams)
}