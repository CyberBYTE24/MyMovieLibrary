package com.cyberbyte.mymovielibrary.useCases

import com.cyberbyte.mymovielibrary.MovieRepository
import com.cyberbyte.mymovielibrary.models.Movie

class GetMovieByIdFromApiUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int): Movie {
        return repository.getMovieById(id)
    }
}