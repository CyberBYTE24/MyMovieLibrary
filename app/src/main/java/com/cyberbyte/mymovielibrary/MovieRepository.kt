package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.data.MovieEntity
import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.models.MovieList

interface MovieRepository {
    suspend fun getMoviesFromApi(): MovieList
    suspend fun getMoviesFromDb(): List<MovieEntity>
    suspend fun saveMoviesToDb(movies: List<MovieEntity>)
    suspend fun getMovieById(id: Int): Movie
}