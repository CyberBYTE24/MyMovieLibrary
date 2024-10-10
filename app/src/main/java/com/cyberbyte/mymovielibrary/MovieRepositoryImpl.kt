package com.cyberbyte.mymovielibrary

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
): MovieRepository {

    override suspend fun getMoviesFromApi(): List<Movie> {
        val movies = remoteDataSource.fetchMovies()
        saveMoviesToDb(movies)
        return movies
    }

    override suspend fun getMoviesFromDb(): List<Movie> {
        return localDataSource.getMovies()
    }

    override suspend fun saveMoviesToDb(movies: List<Movie>) {
        localDataSource.saveMovies(movies)
    }
}