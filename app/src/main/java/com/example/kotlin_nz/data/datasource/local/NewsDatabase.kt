package com.example.kotlin_nz.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlin_nz.domain.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(value = [NewsTypeConverter::class])
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}