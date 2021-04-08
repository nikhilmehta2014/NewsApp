package com.nikhil.newsapp.di

import com.nikhil.newsapp.db.ArticleDatabase
import com.nikhil.newsapp.source.remote.*
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.source.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNewsRepository(remoteDataSource: NewsRemoteDataSource): NewsRepository =
        NewsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource =
        NewsRemoteDataSourceImpl(newsApiService)

    @Provides
    fun provideTechNewsRepository(remoteDataSource: TechNewsRemoteDataSource): TechNewsRepository =
        TechNewsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideTechNewsRemoteDataSource(newsApiService: NewsApiService): TechNewsRemoteDataSource =
        TechNewsRemoteDataSourceImpl(newsApiService)

    @Provides
    fun provideSearchedNewsRepository(remoteDataSource: SearchedNewsRemoteDataSource): SearchedNewsRepository =
        SearchedNewsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideSearchedNewsRemoteDataSource(newsApiService: NewsApiService): SearchedNewsRemoteDataSource =
        SearchedNewsRemoteDataSourceImpl(newsApiService)

    @Provides
    fun provideSavedNewsRepository(db: ArticleDatabase): SavedNewsRepository =
        SavedNewsRepositoryImpl(db)
}