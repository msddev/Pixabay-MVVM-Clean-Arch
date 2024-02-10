package com.example.data.dataSource.remote.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class ImageApiTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var imageApi: ImageApi

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        imageApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `searchImage() response value`() = runTest {
        // Given
        enqueueResponse("/searchApiResponse.json")

        val query = "fruits"
        val page = 1
        val perPage = 20

        // When
        val trips = imageApi.searchImage(searchString = query, page = page, perPage = perPage)

        // Then
        assertEquals(true, trips.images.isNotEmpty())
        assertEquals(5, trips.images.size)
        assertEquals(2305192, trips.images[0].id)
        assertEquals("silviarita", trips.images[0].user)
    }

    private fun enqueueResponse(
        fileName: String,
        headers: Map<String, String> = emptyMap(),
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }

        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }
}