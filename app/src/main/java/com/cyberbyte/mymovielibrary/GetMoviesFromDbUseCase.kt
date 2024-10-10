package com.cyberbyte.mymovielibrary


class GetMoviesFromDbUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<MovieEntity> {
        return repository.getMoviesFromDb()
    }
}