package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.SearchedNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Result

interface SearchedNewsRemoteDataSource {

    suspend fun getSearchedNews(searchedNewsParams: SearchedNewsParams): Result<NewsResponse>
}