package com.rmg.randommoviegenerator.domain.usecases

import com.rmg.randommoviegenerator.data.local.DatabaseMovie
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movie: DatabaseMovie) {
        moviesRepository.deleteMovie(movie)
    }
}