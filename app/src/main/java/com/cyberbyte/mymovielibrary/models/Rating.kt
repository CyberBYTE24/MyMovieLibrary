package com.cyberbyte.mymovielibrary.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("kp")
    @Expose
    val kinopoisk: Float,

    @SerializedName("imdb")
    @Expose
    val imdb: Float,

    @SerializedName("filmCritics")
    @Expose
    val filmCritics: Float,

    @SerializedName("russianFilmCritics")
    @Expose
    val russianFilmCritics: Float
)