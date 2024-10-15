package com.cyberbyte.mymovielibrary.useCases

import com.cyberbyte.mymovielibrary.MovieRepository
import com.cyberbyte.mymovielibrary.data.MovieEntity

class RemoveMoviesFromDbUseCase (private val repository: MovieRepository){
    suspend operator fun invoke(movieEntity: List<MovieEntity>) {
        repository.removeMoviesFromDb(movieEntity)
    }
}