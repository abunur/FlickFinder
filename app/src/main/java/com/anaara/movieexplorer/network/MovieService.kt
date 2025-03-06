package com.anaara.movieexplorer.network

// MovieApiService.kt
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("api/genres")
    suspend fun getGenres(): List<List<Any>>

    @GET("api/movies")
    suspend fun getMovies(
        @Query("limit") limit: Int = 500,
        @Query("from") from: Int = 0,
        @Query("genre") genre: String? = null
    ): List<Movie>
}