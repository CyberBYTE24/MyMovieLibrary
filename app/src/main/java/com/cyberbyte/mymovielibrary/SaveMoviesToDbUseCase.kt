package com.cyberbyte.mymovielibrary

class SaveMoviesToDbUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieEntity: List<MovieEntity>) {
        repository.saveMoviesToDb(movieEntity)
    }
}