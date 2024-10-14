package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.data.MovieDao
import com.cyberbyte.mymovielibrary.data.MovieEntity

class MovieLocalDataSource(private val movieDao: MovieDao) {
    suspend fun getMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }
}