package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.TechNewsRemoteDataSource
import com.nikhil.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TechNewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: TechNewsRemoteDataSource
) : TechNewsRepository {

    override suspend fun getTechNews(techNewsParams: TechNewsParams): Result<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext remoteDataSource.getTechNews(techNewsParams)
        }
}