package com.cyberbyte.mymovielibrary

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovieById(id: Int): Movie
}