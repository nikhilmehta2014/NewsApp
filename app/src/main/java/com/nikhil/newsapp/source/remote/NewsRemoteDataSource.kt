package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Result

interface NewsRemoteDataSource{

    suspend fun getAllNews(newsParams: NewsParams): Result<NewsResponse>

}