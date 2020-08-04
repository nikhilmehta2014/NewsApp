package com.nikhil.newsapp.source.repository

import androidx.lifecycle.LiveData
import com.nikhil.newsapp.db.ArticleDatabase
import com.nikhil.newsapp.models.Article
import javax.inject.Inject

class SavedNewsRepositoryImpl @Inject constructor(
    private val db: ArticleDatabase
) : SavedNewsRepository{

    override suspend fun upsert(article: Article)=
        db.getArticleDao().upsert(article)

    override fun getSavedNews()=db.getArticleDao().getTechArticles()

    override suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}