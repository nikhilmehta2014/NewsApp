package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.SearchedNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SearchedNewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : SearchedNewsRemoteDataSource {

    override suspend fun getSearchedNews(searchedNewsParams: SearchedNewsParams): Result<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = newsApiService.getSearchedNews(
                    searchedNewsParams.searchQuery,
                    searchedNewsParams.page
                )
                if (result.isSuccessful) {
                    val searchedNews = result.body()
                    Result.Success(searchedNews)
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