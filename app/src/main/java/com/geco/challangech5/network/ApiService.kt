package com.geco.challangech5.network


import com.geco.challangech5.API_KEY
import com.geco.challangech5.model.MovieResponse
import com.geco.challangech5.model.detail.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
//    @GET("movie/top_rated")
//    fun getPopularMovies(@Query("api_key") apiKey: String,
//                         @Query("language") language: String,
//                         @Query("page") pageNumber: Int): Call<Result>

    @GET("movie/popular")
    fun getMovie(
//        @Query("api_key") api_key: String = "86afc1d5af482b80283de44b88795287",
        @Query("api_key") api_key: String = API_KEY.apiKey,
    ): Call<MovieResponse>

    @GET("movie/{movieId}")
    fun getDetailMovie(@Path("movieId") movieId: String,
                       @Query("api_key") apiKey: String,
                       @Query("append_to_response") response: String): Call<MovieDetail>

}