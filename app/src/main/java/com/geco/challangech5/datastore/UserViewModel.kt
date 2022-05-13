package com.geco.challangech5.datastore

import androidx.lifecycle.*
import com.geco.challangech5.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUsername() = userRepository.getUsername()
    fun getPassword() = userRepository.getPassword()

    fun setUsername(value: String) = userRepository.setUsername(value)
    fun setPassword(value: String) = userRepository.setPassword(value)

}