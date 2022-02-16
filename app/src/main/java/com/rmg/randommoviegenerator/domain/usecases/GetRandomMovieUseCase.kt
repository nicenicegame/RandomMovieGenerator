package com.rmg.randommoviegenerator.domain.usecases

import com.rmg.randommoviegenerator.data.models.Movie
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(genreId: Int): Movie? {
        return moviesRepository.generateMovieByGenre(genreId).data
    }
}