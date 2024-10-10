package com.cyberbyte.mymovielibrary

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
): MovieRepository {

    override suspend fun getMoviesFromApi(): List<Movie> {
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
}