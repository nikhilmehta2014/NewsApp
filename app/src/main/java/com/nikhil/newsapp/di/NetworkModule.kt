package com.nikhil.newsapp.di

import com.nikhil.newsapp.source.remote.NewsRemoteDataSource
import com.nikhil.newsapp.source.remote.NewsRemoteDataSourceImpl
import com.nikhil.newsapp.source.remote.retrofit.ApiEndPoints
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.source.repository.NewsRepository
import com.nikhil.newsapp.source.repository.NewsRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiEndPoints.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        ))
        .build()

    @Provides
    fun provideNewsApiService(retrofit: Retrofit):NewsApiService = retrofit.create(NewsApiService::class.java)

    @Provides
    fun provideNewsRepository(remoteDataSource: NewsRemoteDataSource):NewsRepository =
        NewsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService):NewsRemoteDataSource =
        NewsRemoteDataSourceImpl(newsApiService)
}