package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.data.MovieEntity

class SaveMoviesToDbUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieEntity: List<MovieEntity>) {
        repository.saveMoviesToDb(movieEntity)
    }
}