package com.nikhil.newsapp.data

data class NewsParams(
    val searchTerm: String,
    val fromDate: String,
    val sortType: String
)