package com.rmg.randommoviegenerator.domain.usecases

import com.rmg.randommoviegenerator.data.models.MovieDetail
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(id: Int): MovieDetail? {
        return moviesRepository.getMovieDetail(id).data
    }
}