package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.utils.Constants
import com.nikhil.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {

    override suspend fun getAllNews(newsParams: NewsParams): Result<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = newsApiService.getAllNews(
                    newsParams.searchTerm,
                    newsParams.fromDate,
                    newsParams.sortType,
                    Constants.API_KEY
                )
                if (result.isSuccessful) {
                    val allNews = result.body()
                    Result.Success(allNews)
                } else {
                    Timber.d("NewsApp: result unsuccessful")
                    Timber.d("NewsApp: error code = ${result.code()}")
                    Timber.d("NewsApp: error message = ${result.message()}")
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Timber.d(exception)
                Result.Error(exception)
            }
        }
}