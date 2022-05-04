package com.geco.challangech5.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geco.challangech5.model.Movie
import com.geco.challangech5.model.MovieResponse
import com.geco.challangech5.network.ApiClient
import com.geco.challangech5.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    fun getMovieData(callback: (List<Movie>) -> Unit) {
        ApiClient.instance.getMovie()
            .enqueue(object: Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>) {
                return when {
                    response.isSuccessful -> {
                        callback(response.body()!!.movies)
                    }
                    else -> {}
                }

            }
        })
    }

    override fun onCleared() {
        super.onCleared()
    }


}