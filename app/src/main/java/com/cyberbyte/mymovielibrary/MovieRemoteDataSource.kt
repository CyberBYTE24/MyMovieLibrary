package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.models.MovieList

class MovieRemoteDataSource(private val movieApiService: MovieApiService) {
    suspend fun fetchMovies(): MovieList {
        return movieApiService.getMovies()
    }

    suspend fun fetchMovieById(id: Int): Movie {
        return movieApiService.getMovieById(id)
    }

}