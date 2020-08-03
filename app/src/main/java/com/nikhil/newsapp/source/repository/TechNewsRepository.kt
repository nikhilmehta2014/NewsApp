package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Result

interface TechNewsRepository {

    suspend fun getTechNews(techNewsParams: TechNewsParams): Result<NewsResponse>

}