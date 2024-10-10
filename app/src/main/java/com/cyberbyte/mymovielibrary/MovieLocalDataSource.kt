package com.cyberbyte.mymovielibrary

class MovieLocalDataSource(private val movieDao: MovieDao) {
    suspend fun getMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }
}