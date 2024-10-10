package com.cyberbyte.mymovielibrary

class MovieRemoteDataSource(private val movieApiService: MovieApiService) {
    suspend fun fetchMovies(): List<Movie> {
        return movieApiService.getMovies()
    }

    suspend fun fetchMovieById(id: Int): Movie {
        return movieApiService.getMovieById(id)
    }

}