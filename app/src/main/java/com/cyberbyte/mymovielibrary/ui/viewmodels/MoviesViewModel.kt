package com.cyberbyte.mymovielibrary.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberbyte.mymovielibrary.data.MovieEntity
import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.useCases.GetMoviesFromApiUseCase
import com.cyberbyte.mymovielibrary.useCases.GetMoviesFromDbUseCase
import com.cyberbyte.mymovielibrary.useCases.RemoveMoviesFromDbUseCase
import com.cyberbyte.mymovielibrary.useCases.SaveMoviesToDbUseCase
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMoviesFromApiUseCase: GetMoviesFromApiUseCase,
    private val saveMoviesToDbUseCase: SaveMoviesToDbUseCase,
    private val getMoviesFromDbUseCase: GetMoviesFromDbUseCase,
    private val removeMoviesFromDbUseCase: RemoveMoviesFromDbUseCase
): ViewModel() {
    private val _movies = MutableLiveData<MutableList<Movie>>()
    val movies: LiveData<MutableList<Movie>> get() = _movies

    private val _favoriteMovies = MutableLiveData<MutableList<MovieEntity>>()
    val favoriteMovies: LiveData<MutableList<MovieEntity>> get() = _favoriteMovies

    fun initMoviesList() {
        loadFavoriteMovies()
        initMoviesListFormApi()
    }

    internal fun loadMovies(pageNum: Int){
        loadMoviesFromApi(pageNum)
    }

    private fun loadMoviesFromApi(page: Int){
        viewModelScope.launch {
            _movies.value?.addAll(getMoviesFromApiUseCase.invoke(page).movies.map {
                val isFavorite = _favoriteMovies.value?.any { favoriteMovie -> favoriteMovie.id == it.id } ?: false
                it.favourite = isFavorite
                if(it.title.isNullOrEmpty()){
                    it.title = it.alternativeName
                }
                return@map it
            })
        }
    }

    private fun initMoviesListFormApi(){
        viewModelScope.launch {
            _movies.value = getMoviesFromApiUseCase.invoke(1).movies.map {
                val isFavorite = _favoriteMovies.value?.any { favoriteMovie -> favoriteMovie.id == it.id } ?: false
                it.favourite = isFavorite
                if(it.title.isNullOrEmpty()){
                    it.title = it.alternativeName
                }
                return@map it
            }.toMutableList()
        }
    }

    private fun loadFavoriteMovies() {
        viewModelScope.launch {
            _favoriteMovies.value = getMoviesFromDbUseCase.invoke().toMutableList()
        }
    }

    private fun saveFavoriteMovies(movies: List<Movie>) {
        viewModelScope.launch {
            saveMoviesToDbUseCase.invoke(movies.map { movie ->
                val entity = MovieEntity(
                    id = movie.id,
                    title = movie.title,
                    description = movie.description,
                    releaseDate = movie.releaseDate,
                    rating = movie.rating?.kinopoisk,
                    posterUrl = movie.poster?.url,
                    favourite = true)
                _favoriteMovies.value?.add(entity)
                entity
            })
        }
    }

    private fun removeFavoriteMovies(movies: List<Movie>) {
        viewModelScope.launch {
            val a = _favoriteMovies.value?.filter { entity ->
                movies.any { movie -> movie.id == entity.id }
            }
            _favoriteMovies.value?.removeAll(a!!)
            removeMoviesFromDbUseCase.invoke(a!!)
        }
    }

    fun onFavouriteClicked(movie: Movie) {
        viewModelScope.launch {
            movie.favourite = !movie.favourite
            if (movie.favourite) {
                saveFavoriteMovies(listOf(movie))
            } else {
                removeFavoriteMovies(listOf(movie))
            }
        }
    }
}