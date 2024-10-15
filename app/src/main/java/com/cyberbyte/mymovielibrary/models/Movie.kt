package com.cyberbyte.mymovielibrary.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val title: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("year")
    @Expose
    val releaseDate: Int?,

    @SerializedName("rating")
    @Expose
    val rating: Rating?,

    @SerializedName("poster")
    @Expose
    val poster: Poster?,

    @SerializedName("favourite")
    @Expose
    var favourite: Boolean
)
