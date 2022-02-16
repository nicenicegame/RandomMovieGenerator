package com.rmg.randommoviegenerator.domain.usecases

import com.rmg.randommoviegenerator.data.local.DatabaseMovie
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(): Flow<List<DatabaseMovie>> {
        return moviesRepository.getFavouriteMovies()
    }
}