package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.utils.Constants
import com.nikhil.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class TechNewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : TechNewsRemoteDataSource {

    override suspend fun getTechNews(techNewsParams: TechNewsParams): Result<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = newsApiService.getTechNews(
                    techNewsParams.country,
                    techNewsParams.category,
                    Constants.API_KEY
                )
                if (result.isSuccessful) {
                    val techNews = result.body()
                    Result.Success(techNews)
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