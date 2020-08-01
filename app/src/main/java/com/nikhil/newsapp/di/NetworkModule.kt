package com.nikhil.newsapp.di

import com.nikhil.newsapp.source.remote.AllNewsRemoteDataSource
import com.nikhil.newsapp.source.remote.AllNewsRemoteDataSourceImpl
import com.nikhil.newsapp.source.remote.retrofit.ApiEndPoints
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.source.repository.AllNewsRepository
import com.nikhil.newsapp.source.repository.AllNewsRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiEndPoints.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .add(Date::class.java, Rfc3339DateJsonAdapter())
//                    .add(CustomDateAdapter())
                    .build()
            )
        )
        .build()

    @Provides
    fun provideNewsApiService(retrofit: Retrofit):NewsApiService = retrofit.create(NewsApiService::class.java)

    @Provides
    fun provideNewsRepository(remoteDataSource: AllNewsRemoteDataSource):AllNewsRepository =
        AllNewsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService):AllNewsRemoteDataSource =
        AllNewsRemoteDataSourceImpl(newsApiService)
}