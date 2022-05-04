package com.geco.challangech5.network


import com.geco.challangech5.API_KEY
import com.geco.challangech5.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovie(
        @Query("api_key") api_key: String = API_KEY.apiKey,
    ): Call<MovieResponse>

}