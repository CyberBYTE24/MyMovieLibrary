package com.cyberbyte.mymovielibrary

class MovieLocalDataSource(private val movieDao: MovieDao) {
    suspend fun getMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }

    suspend fun saveMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }
}