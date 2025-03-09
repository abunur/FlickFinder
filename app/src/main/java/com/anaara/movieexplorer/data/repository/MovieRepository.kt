package com.anaara.movieexplorer.data.repository

import com.anaara.movieexplorer.data.model.Genre
import com.anaara.movieexplorer.data.model.ImdbMovie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    // Get genres with count
    fun getGenres(): Flow<List<Genre>>

    // Fetch genres from API and cache them
    suspend fun refreshGenres()

    // Get movies with pagination
    fun getMovies(genre: String? = null, page: Int, pageSize: Int = 50): Flow<List<ImdbMovie>>

    // Fetch movies from API and cache them
    suspend fun refreshMovies(genre: String? = null, offset: Int = 0, limit: Int = 500)

    // Get total count of movies for a genre
    suspend fun getMoviesCount(genre: String? = null): Int
}