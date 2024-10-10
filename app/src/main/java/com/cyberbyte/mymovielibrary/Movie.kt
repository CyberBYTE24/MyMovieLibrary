package com.cyberbyte.mymovielibrary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MovieRating(

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

data class Poster(

    @SerializedName("previewUrl")
    @Expose
    val previewUrl: String,

    @SerializedName("url")
    @Expose
    val url: String

)

data class Movie(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("year")
    @Expose
    val releaseDate: Int,

    @SerializedName("rating")
    @Expose
    val rating: MovieRating,

    @SerializedName("poster")
    @Expose
    val poster: Poster
)

data class MovieList(

    @SerializedName("docs")
    @Expose
    val movies: List<Movie>

)