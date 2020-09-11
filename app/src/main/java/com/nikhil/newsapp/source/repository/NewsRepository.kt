package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.NewsParams
import com.nikhil.newsapp.models.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getAllNews(newsParams: NewsParams): Flow<NewsResponse>

}