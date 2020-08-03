package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.AllNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Result

interface AllNewsRemoteDataSource{

    suspend fun getAllNews(allNewsParams: AllNewsParams): Result<NewsResponse>

}