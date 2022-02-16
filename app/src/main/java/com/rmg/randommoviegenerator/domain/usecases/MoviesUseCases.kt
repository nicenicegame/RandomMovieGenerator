package com.rmg.randommoviegenerator.domain.usecases

data class MoviesUseCases(
    val addMovieUseCase: AddMovieUseCase,
    val deleteMovieUseCase: DeleteMovieUseCase,
    val getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
    val getGenresUseCase: GetGenresUseCase,
    val getMovieByIdUseCase: GetMovieByIdUseCase,
    val getMovieDetailUseCase: GetMovieDetailUseCase,
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getRandomMovieUseCase: GetRandomMovieUseCase,
    val isFavouriteUseCase: IsFavouriteUseCase
)