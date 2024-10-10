package com.cyberbyte.mymovielibrary

class MovieRemoteDataSource(private val movieApiService: MovieApiService) {
    suspend fun fetchMovies(): MovieList {
        return movieApiService.getMovies()
    }

    suspend fun fetchMovieById(id: Int): Movie {
        return movieApiService.getMovieById(id)
    }

}