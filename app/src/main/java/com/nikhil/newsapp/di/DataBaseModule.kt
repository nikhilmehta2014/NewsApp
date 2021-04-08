package com.nikhil.newsapp.di

import android.app.Application
import androidx.room.Room
import com.nikhil.newsapp.db.ArticleDao
import com.nikhil.newsapp.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideArticleDatabase(application: Application): ArticleDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ArticleDatabase::class.java,
            "Article Database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(articleDatabase: ArticleDatabase): ArticleDao {
        return articleDatabase.getArticleDao()
    }
}