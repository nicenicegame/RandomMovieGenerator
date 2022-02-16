package com.rmg.randommoviegenerator.domain.usecases

import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFavouriteUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(id: Int): Flow<Boolean> {
        return moviesRepository.isFavourite(id)
    }
}