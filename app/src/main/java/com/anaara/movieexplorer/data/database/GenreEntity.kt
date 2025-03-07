package com.anaara.movieexplorer.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey
    val name: String,
    val count: Int
)