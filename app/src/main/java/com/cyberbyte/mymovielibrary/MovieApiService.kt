package com.cyberbyte.mymovielibrary

import retrofit2.http.GET

interface MovieApiService {
    @GET("movies")
    suspend fun getMovies(): List<Movie>
}