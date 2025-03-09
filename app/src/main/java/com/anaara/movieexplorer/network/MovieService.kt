package com.anaara.movieexplorer.network

import com.anaara.movieexplorer.data.model.Genre
import com.anaara.movieexplorer.data.model.ImdbMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("api/genres")
    suspend fun getGenres(): List<List<Genre>>

    @GET("api/movies")
    suspend fun getMovies(
        @Query("limit") limit: Int = 500,
        @Query("from") from: Int = 0,
        @Query("genre") genre: String? = null
    ): List<ImdbMovie>
}