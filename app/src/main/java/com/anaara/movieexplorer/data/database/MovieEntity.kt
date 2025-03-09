package com.anaara.movieexplorer.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "movies")
@TypeConverters(GenreConverter::class)
data class MovieEntity(
    @PrimaryKey
    val id: String,
    val genres: List<String>,
    val release_date: String,
    val title: String,
    val tagline: String,
    val overview: String,
    val url: String,
    val genreFilter: String? = null // This helps us filter by genre
)

// Type converter for List<String> genres
class GenreConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromGenreList(genres: List<String>): String {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genresString: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(genresString, listType)
    }
}