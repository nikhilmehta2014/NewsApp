package com.nikhil.newsapp.source.repository

import androidx.lifecycle.LiveData
import com.nikhil.newsapp.models.Article

interface SavedNewsRepository {

    suspend fun upsert(article: Article) : Long

    fun getSavedNews() : LiveData<List<Article>>

    suspend fun deleteArticle(article: Article)
}