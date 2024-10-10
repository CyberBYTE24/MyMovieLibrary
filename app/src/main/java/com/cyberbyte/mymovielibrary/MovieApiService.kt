package com.cyberbyte.mymovielibrary

import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieApiService {
    @Headers("X-API-KEY: YF9XBHN-PA24TEB-H8VJD84-P5Z8CN9")
    @GET("movie")
    suspend fun getMovies(): MovieList

    suspend fun getMovieById(id: Int): Movie
}