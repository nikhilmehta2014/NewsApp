package com.nikhil.newsapp.source.remote

import com.nikhil.newsapp.data.TechNewsParams
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import com.nikhil.newsapp.utils.Result

interface TechNewsRemoteDataSource{

    suspend fun getTechNews(techNewsParams: TechNewsParams): Result<GetNewsResponseEntity>

}