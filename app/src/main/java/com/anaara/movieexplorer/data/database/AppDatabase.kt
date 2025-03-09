package com.anaara.movieexplorer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, GenreEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
}