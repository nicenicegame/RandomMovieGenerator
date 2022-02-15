package com.rmg.randommoviegenerator.data.repository

import com.rmg.randommoviegenerator.data.local.MovieDao
import com.rmg.randommoviegenerator.data.models.DatabaseMovie
import com.rmg.randommoviegenerator.data.models.Genre
import com.rmg.randommoviegenerator.data.models.Movie
import com.rmg.randommoviegenerator.data.models.MovieDetail
import com.rmg.randommoviegenerator.data.remote.MoviesService
import com.rmg.randommoviegenerator.data.remote.Resource
import com.rmg.randommoviegenerator.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val movieDao: MovieDao
) : MoviesRepository {

    override suspend fun getPopular(): Resource<List<Movie>> {
        return try {
            val movies = moviesService.getPopularMovies().results
            Resource.Success(movies)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getMovieDetail(id: Int): Resource<MovieDetail> {
        return try {
            Resource.Success(moviesService.getMovieById(id))
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getGenres(): Resource<List<Genre>> {
        return try {
            val genres = moviesService.getMovieGenres().genres
            Resource.Success(genres)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getMoviesByGenre(genreId: Int, page: Int): Resource<List<Movie>> {
        return try {
            val response = moviesService.getMoviesByGenre(genreId, page)
            Resource.Success(response.results)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun generateMovieByGenre(genreId: Int): Resource<Movie> {
        return try {
            val randomPage = (1..500).random()
            val moviesByGenres = getMoviesByGenre(genreId, randomPage).data!!.toMutableList()
            val validMovies = moviesByGenres.filter { movie ->
                movie.title != null && movie.posterPath != null && movie.overview != null && movie.backdropPath != null
            }
            Resource.Success(validMovies.random())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun addMovie(movie: DatabaseMovie) {
        movieDao.insert(movie)
    }

    override suspend fun deleteMovie(movie: DatabaseMovie) {
        movieDao.delete(movie)
    }

    override fun getMovieById(id: Int): Flow<DatabaseMovie> {
        return movieDao.getMovieById(id)
    }

    override fun isFavourite(id: Int): Flow<Boolean> {
        return movieDao.isFavourite(id)
    }

    override fun getFavouriteMovies(): Flow<List<DatabaseMovie>> {
        return movieDao.getMovies()
    }
}