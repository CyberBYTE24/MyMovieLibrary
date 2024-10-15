package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.data.MovieEntity
import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.models.MovieList

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
): MovieRepository {

    override suspend fun getMoviesFromApi(): MovieList {
        val movies = remoteDataSource.fetchMovies()
        return movies
    }

    override suspend fun getMovieById(id: Int): Movie {
        return remoteDataSource.fetchMovieById(id)
    }

    override suspend fun getMoviesFromDb(): List<MovieEntity> {
        return localDataSource.getMovies()
    }

    override suspend fun saveMoviesToDb(movies: List<MovieEntity>) {
        localDataSource.saveMovies(movies)
    }

    override suspend fun removeMoviesFromDb(movies: List<MovieEntity>) {
        localDataSource.removeMovies(movies)
    }
}