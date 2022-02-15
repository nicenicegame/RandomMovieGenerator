package com.rmg.randommoviegenerator.di

import com.rmg.randommoviegenerator.data.local.MovieDao
import com.rmg.randommoviegenerator.data.remote.MoviesService
import com.rmg.randommoviegenerator.data.repository.MoviesRepositoryImpl
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesService: MoviesService,
        moviesDao: MovieDao
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesService, moviesDao)
    }
}