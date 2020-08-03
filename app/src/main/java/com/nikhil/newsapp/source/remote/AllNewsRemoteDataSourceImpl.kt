package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.utils.Constants
import com.nikhil.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AllNewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : AllNewsRemoteDataSource {

    override suspend fun getAllNews(allNewsParams: AllNewsParams): Result<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = newsApiService.getAllNews(
                    allNewsParams.searchTerm,
                    allNewsParams.fromDate,
                    allNewsParams.sortType,
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