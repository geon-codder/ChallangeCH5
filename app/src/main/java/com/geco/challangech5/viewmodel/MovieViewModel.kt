package com.geco.challangech5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geco.challangech5.model.Result
import com.geco.challangech5.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    val allMovie = MutableLiveData<List<Result>>()

    fun getMovie(apiKey: String, language: String, pageNumber: Int): MutableLiveData<List<Result>> {
        getDataMovie(apiKey, language, pageNumber)
        return allMovie
    }

    fun getDataMovie(apiKey: String, language: String, pageNumber: Int){
        ApiClient.instance.getPopularMovies(apiKey, language, pageNumber)
            .enqueue(object: Callback<List<Result>> {
                override fun onResponse(
                    call: Call<List<Result>>,
                    response: Response<List<Result>>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if(code == 200){
                        allMovie.postValue(body!!)
                    }else {

                    }
                }

                override fun onFailure(call: Call<List<Result>>, t: Throwable) {
                }
            })
    }


}