package com.nikhil.newsapp.di

import com.nikhil.newsapp.source.remote.*
import com.nikhil.newsapp.source.remote.retrofit.ApiEndPoints
import com.nikhil.newsapp.source.remote.retrofit.NewsApiService
import com.nikhil.newsapp.source.repository.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiEndPoints.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
//                    .add(Date::class.java, Rfc3339DateJsonAdapter())
//                    .add(CustomDateAdapter())
                    .build()
            )
        )
        .client(client)
        .build()

    @Provides
    fun provideNewsApiService(retrofit: Retrofit):NewsApiService = retrofit.create(NewsApiService::class.java)

    @Provides
    fun provideNewsRepository(remoteDataSource: AllNewsRemoteDataSource):AllNewsRepository =
        AllNewsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService):AllNewsRemoteDataSource =
        AllNewsRemoteDataSourceImpl(newsApiService)

    @Provides
    fun provideTechNewsRepository(remoteDataSource: TechNewsRemoteDataSource): TechNewsRepository =
        TechNewsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideTechNewsRemoteDataSource(newsApiService: NewsApiService):TechNewsRemoteDataSource =
        TechNewsRemoteDataSourceImpl(newsApiService)

    @Provides
    fun provideSearchedNewsRepository(remoteDataSource: SearchedNewsRemoteDataSource): SearchedNewsRepository =
        SearchedNewsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideSearchedNewsRemoteDataSource(newsApiService: NewsApiService):SearchedNewsRemoteDataSource =
        SearchedNewsRemoteDataSourceImpl(newsApiService)
}