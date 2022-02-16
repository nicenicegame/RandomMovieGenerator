package com.rmg.randommoviegenerator.domain.usecases

import com.rmg.randommoviegenerator.data.models.Movie
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(): List<Movie> {
        return moviesRepository.getPopular().data ?: emptyList()
    }
}