package com.cyberbyte.mymovielibrary.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val description: String?,
    val releaseDate: Int?,
    val rating: Float?,
    val posterUrl: String?,
    val favourite: Boolean
)