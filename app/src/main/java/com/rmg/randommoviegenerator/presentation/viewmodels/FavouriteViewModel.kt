package com.rmg.randommoviegenerator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rmg.randommoviegenerator.data.repository.MoviesRepositoryImpl
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {

    val favouriteMovies = moviesRepository.getFavouriteMovies().asLiveData()
}