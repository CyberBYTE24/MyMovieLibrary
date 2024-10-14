package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.models.Movie

interface MovieListener {
    fun onMovieClicked(movie: Movie)
    fun onFavouriteClicked(movie: Movie)
}