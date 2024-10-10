package com.cyberbyte.mymovielibrary

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String
)