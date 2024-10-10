package com.cyberbyte.mymovielibrary

interface MovieRepository {
    suspend fun getMoviesFromApi(): List<Movie>
    suspend fun getMoviesFromDb(): List<MovieEntity>
    suspend fun saveMoviesToDb(movies: List<MovieEntity>)
    suspend fun getMovieById(id: Int): Movie
}