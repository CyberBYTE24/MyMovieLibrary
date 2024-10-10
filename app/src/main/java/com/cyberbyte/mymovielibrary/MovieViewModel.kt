package com.cyberbyte.mymovielibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMoviesFromApiUseCase: GetMoviesFromApiUseCase,
    private val getMovieByIdFromApiUseCase: GetMovieByIdFromApiUseCase,
    private val saveMoviesToDbUseCase: SaveMoviesToDbUseCase
): ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _favoriteMovies = MutableLiveData<List<MovieEntity>>()
    val favoriteMovies: LiveData<List<MovieEntity>> get() = _favoriteMovies

    fun loadMovies() {
        viewModelScope.launch {
            _movies.value = getMoviesFromApiUseCase.invoke()
        }
    }
    fun loadFavoriteMovies() {
        viewModelScope.launch {
            _movies.value = getMoviesFromApiUseCase.invoke()
        }
    }

}