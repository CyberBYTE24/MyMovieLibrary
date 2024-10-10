package com.cyberbyte.mymovielibrary

interface MovieRepository {
    suspend fun getMoviesFromApi(): MovieList
    suspend fun getMoviesFromDb(): List<MovieEntity>
    suspend fun saveMoviesToDb(movies: List<MovieEntity>)
    suspend fun getMovieById(id: Int): Movie
}