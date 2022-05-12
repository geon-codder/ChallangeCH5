package com.geco.challangech5.datastore

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class UserViewModel(private val pref: CounterDataStoreManager) : ViewModel() {

    fun getUsername(): LiveData<Any>{
        return pref.getUsername().asLiveData()
    }
    fun getPassword(): LiveData<Any>{
        return pref.getPassword().asLiveData()
    }

    fun getLoginStatus(): LiveData<Int>{
        return pref.getLoginStatus().asLiveData()
    }


    fun saveLoginStats(value: Int) {
        viewModelScope.launch {
            pref.setLoginStatus(value)
        }
    }
}