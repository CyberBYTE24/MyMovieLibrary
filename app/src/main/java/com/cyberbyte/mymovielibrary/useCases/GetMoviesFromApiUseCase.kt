package com.cyberbyte.mymovielibrary.useCases

import com.cyberbyte.mymovielibrary.MovieRepository
import com.cyberbyte.mymovielibrary.models.MovieList

class GetMoviesFromApiUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): MovieList {
        return repository.getMoviesFromApi()
    }
}