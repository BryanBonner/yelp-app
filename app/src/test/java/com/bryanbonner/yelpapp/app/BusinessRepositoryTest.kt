package com.bryanbonner.yelpapp.app

import com.bryanbonner.yelpapp.app.data.BusinessRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest

@RunWith(JUnit4::class)
class BusinessRepositoryTest: KoinTest {

    lateinit var mockServer: MockWebServer

    private lateinit var repository: BusinessRepository
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()
//        startKoin(configureAppComponent(getMockUrl()))
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
        StandAloneContext.stopKoin()
    }


    @Test
    fun `get businesses and succeed`() {

    }

}