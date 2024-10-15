package com.cyberbyte.mymovielibrary.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("previewUrl")
    @Expose
    val previewUrl: String?,

    @SerializedName("url")
    @Expose
    val url: String?
)
