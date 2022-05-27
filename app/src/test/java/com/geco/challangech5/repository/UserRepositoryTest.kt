package com.geco.challangech5.repository

import android.content.Context
import com.geco.challangech5.datastore.CounterDataStoreManager
import io.mockk.verify
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before

class UserRepositoryTest : TestCase() {

    private lateinit var context: Context
    private lateinit var pref: CounterDataStoreManager
    private lateinit var userRepository: UserRepository

    @Before
    override fun setUp(){
        pref = CounterDataStoreManager(context)
        userRepository = UserRepository(pref)
    }

    fun testGetUsername() {
        userRepository.getUsername()
    }
}