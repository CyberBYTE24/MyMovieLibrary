package com.cyberbyte.mymovielibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase
): ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun loadMovies() {
        viewModelScope.launch {
            _movies.value = fetchMoviesUseCase.execute()
        }
    }
}