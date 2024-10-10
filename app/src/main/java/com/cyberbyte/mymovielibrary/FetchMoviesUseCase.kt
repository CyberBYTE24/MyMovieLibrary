package com.cyberbyte.mymovielibrary

class FetchMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute(): List<Movie> {
        return repository.getMoviesFromApi()
    }
}