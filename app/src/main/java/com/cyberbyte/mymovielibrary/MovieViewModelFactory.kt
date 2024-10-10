package com.cyberbyte.mymovielibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieViewModelFactory(
    private val getMoviesFromApiUseCase: GetMoviesFromApiUseCase,
    private val getMovieByIdFromApiUseCase: GetMovieByIdFromApiUseCase,
    private val saveMoviesToDbUseCase: SaveMoviesToDbUseCase,
    private val getMoviesFromDbUseCase: GetMoviesFromDbUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(getMoviesFromApiUseCase, getMovieByIdFromApiUseCase, saveMoviesToDbUseCase, getMoviesFromDbUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}