package com.cyberbyte.mymovielibrary.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("docs")
    @Expose
    val movies: List<Movie>
)
