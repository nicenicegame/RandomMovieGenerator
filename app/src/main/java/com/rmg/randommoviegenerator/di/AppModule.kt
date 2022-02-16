package com.rmg.randommoviegenerator.di

import com.rmg.randommoviegenerator.data.local.MovieDao
import com.rmg.randommoviegenerator.data.remote.MoviesService
import com.rmg.randommoviegenerator.data.repository.MoviesRepositoryImpl
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import com.rmg.randommoviegenerator.domain.usecases.*
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

    @Provides
    @Singleton
    fun provideMoviesUseCases(
        moviesRepository: MoviesRepository
    ): MoviesUseCases {
        return MoviesUseCases(
            addMovieUseCase = AddMovieUseCase(moviesRepository),
            deleteMovieUseCase = DeleteMovieUseCase(moviesRepository),
            getFavouriteMoviesUseCase = GetFavouriteMoviesUseCase(moviesRepository),
            getGenresUseCase = GetGenresUseCase(moviesRepository),
            getMovieByIdUseCase = GetMovieByIdUseCase(moviesRepository),
            getMovieDetailUseCase = GetMovieDetailUseCase(moviesRepository),
            getPopularMoviesUseCase = GetPopularMoviesUseCase(moviesRepository),
            getRandomMovieUseCase = GetRandomMovieUseCase(moviesRepository),
            isFavouriteUseCase = IsFavouriteUseCase(moviesRepository)
        )
    }
}