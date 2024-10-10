package com.cyberbyte.mymovielibrary

interface MovieRepository {
    suspend fun getMoviesFromApi(): List<Movie>
    suspend fun getMoviesFromDb(): List<Movie>
    suspend fun saveMoviesToDb(movies: List<Movie>)
}