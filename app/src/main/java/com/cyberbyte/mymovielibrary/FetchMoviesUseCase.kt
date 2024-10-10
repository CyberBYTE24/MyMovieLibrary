package com.cyberbyte.mymovielibrary

class FetchMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return repository.getMoviesFromApi()
    }
}