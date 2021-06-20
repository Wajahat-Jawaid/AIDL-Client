package com.wajahat.golootloandroidtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wajahat.golootloandroidtest.api.PhotosService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit

@RunWith(JUnit4::class)
class PhotoServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var service: PhotosService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("https://picsum.photos/"))
            .build()
            .create(PhotosService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestPhotos() {
        runBlocking {
            enqueueResponse("photos.json")
            val resultResponse = service.getPhotos(1, 20).body()

            val request = mockWebServer.takeRequest()
            assertNotNull(resultResponse)
            assertThat(request.path, `is`("v2/list/"))
        }
    }

    private fun enqueueResponse(fileName: String) {
        val inputStream = App.getInstance().assets.open(fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        mockWebServer.enqueue(
            mockResponse.setBody(
                source.readString(Charsets.UTF_8)
            )
        )
    }
}