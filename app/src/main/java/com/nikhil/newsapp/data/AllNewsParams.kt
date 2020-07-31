package com.nikhil.newsapp.data

data class AllNewsParams(
    val searchTerm: String,
    val fromDate: String,
    val sortType: String
)