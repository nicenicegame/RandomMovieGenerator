package com.rmg.randommoviegenerator.domain.usecases

import com.rmg.randommoviegenerator.data.models.Genre
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(): List<Genre> {
        return moviesRepository.getGenres().data ?: emptyList()
    }
}