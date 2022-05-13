package com.geco.challangech5.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.geco.challangech5.datastore.CounterDataStoreManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository(private val pref: CounterDataStoreManager) {

    fun getUsername(): LiveData<Any> {
        return pref.getUsername().asLiveData()
    }

    fun getPassword(): LiveData<Any> {
        return pref.getPassword().asLiveData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun setUsername(value: String) {
        GlobalScope.launch {
            pref.setUsername(value)
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun setPassword(value: String) {
        GlobalScope.launch {
            pref.setPassword(value)
        }
    }

}