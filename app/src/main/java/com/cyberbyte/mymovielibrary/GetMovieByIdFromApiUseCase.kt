package com.cyberbyte.mymovielibrary

class GetMovieByIdFromApiUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int): Movie {
        return repository.getMovieById(id)
    }
}