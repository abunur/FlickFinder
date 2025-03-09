package com.anaara.movieexplorer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE (:genreFilter IS NULL OR genreFilter = :genreFilter) ORDER BY title ASC LIMIT :limit OFFSET :offset")
    fun getMoviesByGenre(genreFilter: String?, limit: Int, offset: Int): Flow<List<MovieEntity>>

    @Query("SELECT COUNT(*) FROM movies WHERE (:genreFilter IS NULL OR genreFilter = :genreFilter)")
    suspend fun getMoviesCount(genreFilter: String?): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE genreFilter = :genreFilter")
    suspend fun clearMoviesByGenre(genreFilter: String?)
}
