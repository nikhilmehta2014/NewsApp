package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRemoteDataSource{

    suspend fun getAllNews(newsParams: NewsParams): Flow<NewsResponse>

}