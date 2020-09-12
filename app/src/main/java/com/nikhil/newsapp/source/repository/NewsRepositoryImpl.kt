package com.nikhil.newsapp.source.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.NewsRemoteDataSource
import com.nikhil.newsapp.utils.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getAllNews(newsParams: NewsParams): Flow<NewsResponse> =
        remoteDataSource.getAllNews(newsParams)

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCurrentDate(): Flow<String> {
        return flow {
            val currentDate = DateUtils.getCurrentDate()
            emit(currentDate)
        }
    }
}