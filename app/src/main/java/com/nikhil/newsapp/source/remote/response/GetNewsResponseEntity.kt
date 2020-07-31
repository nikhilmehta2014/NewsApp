package com.nikhil.newsapp.source.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetNewsResponseEntity(
    val status: String? = "",
    val totalResults: Int,
    val articles: List<Article>? = null
) {
    data class Article(
        val source: Source? = null,
        val author: String? = "",
        val title: String? = "",
        val description: String? = "",
        val url: String? = "",
        val urlToImage: String? = "",
        val publishedAt: String? = "",
        val content: String? = ""
    ) {
        data class Source(
            val id: String? = "",
            val name: String? = ""
        )
    }
}