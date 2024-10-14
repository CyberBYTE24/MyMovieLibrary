package com.cyberbyte.mymovielibrary.useCases

import com.cyberbyte.mymovielibrary.data.MovieEntity
import com.cyberbyte.mymovielibrary.MovieRepository


class GetMoviesFromDbUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<MovieEntity> {
        return repository.getMoviesFromDb()
    }
}