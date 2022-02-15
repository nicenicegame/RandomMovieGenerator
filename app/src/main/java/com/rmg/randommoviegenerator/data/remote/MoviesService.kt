package com.rmg.randommoviegenerator.data.remote

import com.rmg.randommoviegenerator.BuildConfig
import com.rmg.randommoviegenerator.data.models.GenreResponse
import com.rmg.randommoviegenerator.data.models.MovieDetail
import com.rmg.randommoviegenerator.data.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/top_rated")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse

    @GET("movie/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieDetail

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}