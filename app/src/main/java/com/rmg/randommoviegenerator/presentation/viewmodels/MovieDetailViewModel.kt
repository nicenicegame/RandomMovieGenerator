package com.rmg.randommoviegenerator.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    moviesRepository: MoviesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>(MOVIE_ID_SAVED_STATE_KEY) ?: 0

    val movie = moviesRepository.getMovieById(movieId).asLiveData()

    companion object {
        private const val MOVIE_ID_SAVED_STATE_KEY = "movieId"
    }
}