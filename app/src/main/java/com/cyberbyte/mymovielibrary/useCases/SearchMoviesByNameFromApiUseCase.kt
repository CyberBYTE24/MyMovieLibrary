package com.cyberbyte.mymovielibrary.useCases

import com.cyberbyte.mymovielibrary.MovieRepository
import com.cyberbyte.mymovielibrary.models.MovieList

class SearchMoviesByNameFromApiUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(nameWildcard: String): MovieList {
        return repository.searchMoviesByNameFromApi(nameWildcard)
    }
}