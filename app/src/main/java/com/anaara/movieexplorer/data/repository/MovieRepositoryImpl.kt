package com.anaara.movieexplorer.data.repository

import com.anaara.movieexplorer.data.database.GenreDao
import com.anaara.movieexplorer.data.database.GenreEntity
import com.anaara.movieexplorer.data.database.MovieDao
import com.anaara.movieexplorer.data.database.MovieEntity
import com.anaara.movieexplorer.data.model.Genre
import com.anaara.movieexplorer.data.model.ImdbMovie
import com.anaara.movieexplorer.network.MovieApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao
) : MovieRepository {

    // Keeps track of which pages we've already loaded for each genre
    private val loadedPages = mutableMapOf<String?, MutableSet<Int>>()

    // Check if we need to load this page from API
    private fun needsRefresh(genre: String?, page: Int): Boolean {
        val pages = loadedPages[genre] ?: mutableSetOf()
        return !pages.contains(page)
    }

    // Mark a page as loaded
    private fun markPageLoaded(genre: String?, page: Int) {
        val pages = loadedPages.getOrPut(genre) { mutableSetOf() }
        pages.add(page)
    }

    override fun getGenres(): Flow<List<Genre>> {
        return genreDao.getAllGenres().map { entities ->
            entities.map { Genre(it.name, it.count) }
        }
    }

    override suspend fun refreshGenres() {
        try {
            val genreResponse = apiService.getGenres()
            val genres = genreResponse.map {
                GenreEntity(
                    name = it[0] as String,
                    count = (it[1] as Double).toInt()
                )
            }
            genreDao.clearGenres()
            genreDao.insertGenres(genres)
        } catch (e: Exception) {
            // Handle exception
        }
    }

    override fun getMovies(genre: String?, page: Int, pageSize: Int): Flow<List<ImdbMovie>> {
        val offset = page * pageSize

        // Check if we need to load this page from API
        if (needsRefresh(genre, page)) {
            CoroutineScope(Dispatchers.IO).launch {
                refreshMovies(genre, offset, pageSize)
                markPageLoaded(genre, page)
            }
        }

        return movieDao.getMoviesByGenre(genre, pageSize, offset).map { entities ->
            entities.map {
                ImdbMovie(
                    id = it.id,
                    genres = it.genres,
                    release_date = it.release_date,
                    title = it.title,
                    tagline = it.tagline,
                    overview = it.overview,
                    url = it.url
                )
            }
        }
    }

    override suspend fun refreshMovies(genre: String?, offset: Int, limit: Int) {
        try {
            val movies = apiService.getMovies(limit = limit, from = offset, genre = genre)

            // Create entities with proper genre filter
            val movieEntities = movies.map { movie ->
                MovieEntity(
                    id = movie.id,
                    genres = movie.genres,
                    release_date = movie.release_date,
                    title = movie.title,
                    tagline = movie.tagline,
                    overview = movie.overview,
                    url = movie.url,
                    genreFilter = genre
                )
            }

            // If it's the first page, clear existing data for this genre filter
            if (offset == 0) {
                movieDao.clearMoviesByGenre(genre)
            }

            movieDao.insertMovies(movieEntities)

            // If we got a full page, there might be more
            if (movies.size == limit && limit == 500) {
                refreshMovies(genre, offset + limit)
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }

    override suspend fun getMoviesCount(genre: String?): Int {
        return movieDao.getMoviesCount(genre)
    }
}