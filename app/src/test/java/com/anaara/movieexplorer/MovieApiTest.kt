package com.anaara.movieexplorer

import com.anaara.movieexplorer.data.model.ImdbMovie
import com.anaara.movieexplorer.network.MovieApiService
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MovieApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieApiService: MovieApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        movieApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getGenres returns list of genres`() = runBlocking {
        // Arrange
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("genres.json").content)
        mockWebServer.enqueue(mockResponse)

        // Act
        val actualResponse: List<List<Any>> = movieApiService.getGenres()

        // Assert
        Truth.assertThat(actualResponse).isNotNull()
        Truth.assertThat(actualResponse.size).isEqualTo(2)
    }

    @Test
    fun `getGenres returns error`() = runBlocking {
        // Arrange
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(mockResponse)

        // Act
        var exceptionCaught = false
        try {
            movieApiService.getGenres()
        } catch (e: Exception) {
            exceptionCaught = true
        }

        // Assert
        Truth.assertThat(exceptionCaught).isTrue()
    }

    @Test
    fun `getMovies returns list of movies`() = runBlocking {
        // Arrange
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("movies.json").content)
        mockWebServer.enqueue(mockResponse)

        // Act
        val actualResponse: List<ImdbMovie> = movieApiService.getMovies()

        // Assert
        Truth.assertThat(actualResponse).isNotNull()
        Truth.assertThat(actualResponse.size).isEqualTo(2)
    }

    @Test
    fun `getMovies returns error`() = runBlocking {
        // Arrange
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(mockResponse)

        // Act
        var exceptionCaught = false
        try {
            movieApiService.getMovies()
        } catch (e: Exception) {
            exceptionCaught = true
        }

        // Assert
        Truth.assertThat(exceptionCaught).isTrue()
    }

    @Test
    fun `getGenres returns timeout error`() = runBlocking {
        // Arrange
        // Do not enqueue any responses, this will cause a timeout error

        // Act
        var exceptionCaught = false
        try {
            movieApiService.getGenres()
        } catch (e: SocketTimeoutException) {
            exceptionCaught = true
        }

        // Assert
        Truth.assertThat(exceptionCaught).isTrue()
    }

    @Test
    fun `getMovies returns timeout error`() = runBlocking {
        // Arrange
        // Do not enqueue any responses, this will cause a timeout error

        // Act
        var exceptionCaught = false
        try {
            movieApiService.getMovies()
        } catch (e: SocketTimeoutException) {
            exceptionCaught = true
        }

        // Assert
        Truth.assertThat(exceptionCaught).isTrue()
    }

    @Test
    fun `getGenres returns unknown host error`() = runBlocking {
        // Arrange
        movieApiService = Retrofit.Builder()
            .baseUrl("http://invalid-url-for-testing.com/") // Force an unknown host error
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)

        // Act
        var exceptionCaught = false
        try {
            movieApiService.getGenres()
        } catch (e: UnknownHostException) {
            exceptionCaught = true
        }

        // Assert
        Truth.assertThat(exceptionCaught).isTrue()
    }
}