package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.models.MovieList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApiService {
    @Headers("X-API-KEY: YF9XBHN-PA24TEB-H8VJD84-P5Z8CN9")
    @GET("movie?page=1&limit=40&sortField=rating.kp&sortType=-1")

    suspend fun getMovies(): MovieList

    suspend fun getMovieById(id: Int): Movie
}