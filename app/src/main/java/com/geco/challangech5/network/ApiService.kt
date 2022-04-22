package com.geco.challangech5.network

import com.geco.challangech5.model.MovieRequest
import com.geco.challangech5.model.Result
import com.geco.challangech5.model.detail.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    fun getPopularMovies(@Query("api_key") apiKey: String,
                         @Query("language") language: String,
                         @Query("page") pageNumber: Int): Call<List<Result>>

    @GET("movie/{movieId}")
    fun getDetailMovie(@Path("movieId") movieId: String,
                       @Query("api_key") apiKey: String,
                       @Query("append_to_response") response: String): Call<MovieDetail>

}