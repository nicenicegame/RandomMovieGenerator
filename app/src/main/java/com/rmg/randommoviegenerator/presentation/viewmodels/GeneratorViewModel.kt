package com.rmg.randommoviegenerator.presentation.viewmodels

import androidx.lifecycle.*
import com.rmg.randommoviegenerator.data.models.Genre
import com.rmg.randommoviegenerator.data.models.Movie
import com.rmg.randommoviegenerator.data.remote.Resource
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratorViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _snackBarMessage = MutableLiveData<String?>()
    val snackBarMessage: LiveData<String?> get() = _snackBarMessage

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    private val _generatedMovie = MutableLiveData<Movie>()
    val generatedMovie: LiveData<Movie> get() = _generatedMovie

    private val _isGenerating = MutableLiveData(false)
    val isGenerating: LiveData<Boolean> get() = _isGenerating

    private val _selectedGenre = MutableLiveData<Genre>()

    val isFavourite = _generatedMovie.switchMap {
        moviesRepository.isFavourite(it.id).asLiveData()
    }

    init {
        fetchGenres()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            when (val resource = moviesRepository.getGenres()) {
                is Resource.Success -> _genres.value = resource.data!!
                is Resource.Error -> Unit
            }
        }
    }

    fun setSelectedGenre(genre: Genre) {
        _selectedGenre.value = genre
    }

    fun generateMovie() {
        _selectedGenre.value?.let {
            viewModelScope.launch {
                _isGenerating.value = true
                when (val resource = moviesRepository.generateMovieByGenre(it.id)) {
                    is Resource.Success -> _generatedMovie.value = resource.data!!
                    is Resource.Error -> Unit
                }
                _isGenerating.value = false
            }
        }
    }

    fun onAddMovieToFavourite() {
        _generatedMovie.value?.let {
            viewModelScope.launch {
                moviesRepository.addMovie(it.toDatabaseModel())
                delay(300)
                _snackBarMessage.value = "Marked \"${it.title}\" as favourite."
            }
        }
    }

    fun onUndoClick() {
        isFavourite.value?.let {
            viewModelScope.launch {
                delay(300)
                val movie = _generatedMovie.value!!
                moviesRepository.deleteMovie(movie.toDatabaseModel())
            }
        }
    }

    fun closeSnackBar() {
        _snackBarMessage.value = null
    }
}