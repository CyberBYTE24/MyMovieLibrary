package com.cyberbyte.mymovielibrary

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberbyte.mymovielibrary.data.MovieEntity
import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.useCases.GetMovieByIdFromApiUseCase
import com.cyberbyte.mymovielibrary.useCases.GetMoviesFromApiUseCase
import com.cyberbyte.mymovielibrary.useCases.GetMoviesFromDbUseCase
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.android.subDI
import org.kodein.di.instance
import java.util.logging.Logger

class MovieViewModel(
    private val getMoviesFromApiUseCase: GetMoviesFromApiUseCase,
    private val getMovieByIdFromApiUseCase: GetMovieByIdFromApiUseCase,
    private val saveMoviesToDbUseCase: SaveMoviesToDbUseCase,
    private val getMoviesFromDbUseCase: GetMoviesFromDbUseCase
): ViewModel() {

    //TODO (DI implement in fragment)
    //override val di:DI by subDI(closestDI())

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _favoriteMovies = MutableLiveData<List<MovieEntity>>()
    val favoriteMovies: LiveData<List<MovieEntity>> get() = _favoriteMovies

    fun loadMovies() {
        viewModelScope.launch {
            _movies.value = getMoviesFromApiUseCase.invoke().movies
        }
    }
    fun loadFavoriteMovies() {
        viewModelScope.launch {
            _favoriteMovies.value = getMoviesFromDbUseCase.invoke()
        }
    }

    fun saveFavoriteMovies() {
        viewModelScope.launch {
            saveMoviesToDbUseCase.invoke(_favoriteMovies.value!!)
        }
    }

}