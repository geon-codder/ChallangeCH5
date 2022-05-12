package com.geco.challangech5.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CounterDataStoreManager(private val context: Context) {

    suspend fun setUsername(name: String) {
        context.counterDataStore.edit { preferences ->
            preferences[NAME_KEY] = setOf(name)
        }
    }
    suspend fun setPassword(pass: String){
        context.counterDataStore.edit {preferences ->
            preferences[PASS_KEY] = setOf(pass)
        }
    }
    suspend fun setLoginStatus(status: Int){
        context.counterDataStore.edit {preferences ->
            preferences[LOGINSTATS_KEY] = status
        }
    }

    fun getUsername(): Flow<Any> {
        return context.counterDataStore.data.map { preferences ->
            preferences[NAME_KEY] ?: ""
        }
    }
    fun getPassword(): Flow<Any> {
        return context.counterDataStore.data.map { preferences ->
            preferences[PASS_KEY] ?: ""
        }
    }
    fun getLoginStatus(): Flow<Int> {
        return context.counterDataStore.data.map { preferences ->
            preferences[LOGINSTATS_KEY] ?: 0
        }
    }


    companion object {
        private const val DATASTORE_NAME = "counter_preferences"

        private val NAME_KEY = stringSetPreferencesKey("name_key")
        private val PASS_KEY = stringSetPreferencesKey("pass_key")

        val LOGINSTATS_KEY = intPreferencesKey("loginStats_key")


        private val Context.counterDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}
