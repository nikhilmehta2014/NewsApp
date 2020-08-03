package com.nikhil.newsapp.models

data class NewsResponse(
    val status: String?=null,
    val totalResults: Int?=0,
    val articles: List<Article>?=null
)