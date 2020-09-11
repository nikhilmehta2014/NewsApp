package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Result

interface NewsRepository {

    suspend fun getAllNews(newsParams: NewsParams): Result<NewsResponse>

}