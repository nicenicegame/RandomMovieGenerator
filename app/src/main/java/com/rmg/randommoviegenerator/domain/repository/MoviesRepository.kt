package com.rmg.randommoviegenerator.domain.repository

import com.rmg.randommoviegenerator.data.local.DatabaseMovie
import com.rmg.randommoviegenerator.data.models.Genre
import com.rmg.randommoviegenerator.data.models.Movie
import com.rmg.randommoviegenerator.data.models.MovieDetail
import com.rmg.randommoviegenerator.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopular(): Resource<List<Movie>>

    suspend fun getMovieDetail(id: Int): Resource<MovieDetail>

    suspend fun getGenres(): Resource<List<Genre>>

    suspend fun getMoviesByGenre(genreId: Int, page: Int): Resource<List<Movie>>

    suspend fun generateMovieByGenre(genreId: Int): Resource<Movie>

    suspend fun addMovie(movie: DatabaseMovie)

    suspend fun deleteMovie(movie: DatabaseMovie)

    fun getMovieById(id: Int): Flow<DatabaseMovie>

    fun getFavouriteMovies(): Flow<List<DatabaseMovie>>

    fun isFavourite(id: Int): Flow<Boolean>
}