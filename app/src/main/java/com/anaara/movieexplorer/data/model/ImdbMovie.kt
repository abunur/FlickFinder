package com.anaara.movieexplorer.data.model

data class ImdbMovie(
    val title: String,
    val year: Int,
    val overview: String,
    val genres: List<String>
)