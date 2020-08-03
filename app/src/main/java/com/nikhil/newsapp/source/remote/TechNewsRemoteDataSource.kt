package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Result

interface TechNewsRemoteDataSource{

    suspend fun getTechNews(techNewsParams: TechNewsParams): Result<NewsResponse>

}