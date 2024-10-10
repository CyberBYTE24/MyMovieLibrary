package com.cyberbyte.mymovielibrary

class GetMoviesFromApiUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): MovieList {
        return repository.getMoviesFromApi()
    }
}