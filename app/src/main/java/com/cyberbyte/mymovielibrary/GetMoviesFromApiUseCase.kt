package com.cyberbyte.mymovielibrary

class GetMoviesFromApiUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return repository.getMoviesFromApi()
    }
}