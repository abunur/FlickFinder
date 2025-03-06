package com.anaara.movieexplorer.data.model

data class Movie(
    val id: String,
    val genres: List<String>,
    val release_date: String,
    val title: String,
    val tagline: String,
    val overview: String,
    val url: String
) {
    fun getReleaseYear(): String {
        return if (release_date.length >= 4) {
            release_date.substring(0, 4)
        } else {
            "N/A"
        }
    }
}