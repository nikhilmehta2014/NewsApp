package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Result

interface AllNewsRepository {

    suspend fun getAllNews(allNewsParams: AllNewsParams): Result<NewsResponse>

}