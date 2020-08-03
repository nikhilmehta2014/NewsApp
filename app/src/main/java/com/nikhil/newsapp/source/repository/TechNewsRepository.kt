package com.nikhil.newsapp.source.repository

import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import com.nikhil.newsapp.utils.Result

interface TechNewsRepository {

    suspend fun getTechNews(techNewsParams: TechNewsParams): Result<GetNewsResponseEntity>

}