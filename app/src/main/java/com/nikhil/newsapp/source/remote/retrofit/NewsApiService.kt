package com.nikhil.newsapp.source.remote.retrofit

import com.nikhil.newsapp.models.NewsResponse
import com.nikhil.newsapp.utils.Constants.API_KEY
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
    ): NewsResponse

    @GET("v2/top-headlines")
    suspend fun getTechNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getBitcoinNews(
        @Query("q") searchTerm: String,
        @Query("from") fromDate: String,
        @Query("sortBy") sortType: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}
