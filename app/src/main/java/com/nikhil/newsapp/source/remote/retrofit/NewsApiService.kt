package com.nikhil.newsapp.source.remote.retrofit

import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q") searchTerm: String,
        @Query("from") fromDate: String,
        @Query("sortBy") sortType: String,
        @Query("apiKey") apiKey: String
    ): Response<GetNewsResponseEntity>
}
