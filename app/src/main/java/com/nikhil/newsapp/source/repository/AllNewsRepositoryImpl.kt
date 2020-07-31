package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.source.remote.AllNewsRemoteDataSource
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import com.nikhil.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllNewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AllNewsRemoteDataSource
) : AllNewsRepository {

    override suspend fun getAllNews(allNewsParams: AllNewsParams): Result<GetNewsResponseEntity> =
        withContext(Dispatchers.IO) {
            return@withContext remoteDataSource.getAllNews(allNewsParams)
        }
}