package com.rmg.randommoviegenerator.di

import com.rmg.randommoviegenerator.data.remote.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoviesService(): MoviesService = Retrofit.Builder()
        .baseUrl(MoviesService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoviesService::class.java)
}