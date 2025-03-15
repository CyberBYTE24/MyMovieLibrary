package com.cyberbyte.mymovielibrary

import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.models.MovieList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApiService {

    @Headers("X-API-KEY: YF9XBHN-PA24TEB-H8VJD84-P5Z8CN9")
    @GET("movie?limit=20&sortField=rating.kp&sortType=-1")
    suspend fun getMovies(@Query("page") page: Int): MovieList

    @Headers("X-API-KEY: YF9XBHN-PA24TEB-H8VJD84-P5Z8CN9")
    @GET("movie?limit=20&sortField=rating.kp&sortType=-1")
    suspend fun searchMoviesByName(@Query("search") nameWildcard: String): MovieList

    suspend fun getMovieById(id: Int): Movie
}