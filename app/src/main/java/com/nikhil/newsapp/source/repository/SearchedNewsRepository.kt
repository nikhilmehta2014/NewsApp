package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.SearchedNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Result

interface SearchedNewsRepository {

    suspend fun getSearchedNews(searchedNewsParams: SearchedNewsParams): Result<NewsResponse>
}