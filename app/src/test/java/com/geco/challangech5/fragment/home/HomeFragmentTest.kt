package com.geco.challangech5.fragment.home

import com.geco.challangech5.model.MovieResponse
import com.geco.challangech5.network.ApiService
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class HomeFragmentTest : TestCase() {

    private lateinit var service: ApiService

    @Before
    override fun setUp(){
        service = mockk()
    }

    @Test
    fun testGetMovieData(): Unit = runBlocking {
        val respAllMovie = mockk<MovieResponse>()

        every{
            runBlocking {
                service.getMovie()
            }
        }

    }
}