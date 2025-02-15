package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.models.MovieList

class MovieRemoteDataSource(private val movieApiService: MovieApiService) {
    suspend fun fetchMovies(page: Int): MovieList {
        return movieApiService.getMovies(page)
    }

    suspend fun fetchMovieById(id: Int): Movie {
        return movieApiService.getMovieById(id)
    }

}