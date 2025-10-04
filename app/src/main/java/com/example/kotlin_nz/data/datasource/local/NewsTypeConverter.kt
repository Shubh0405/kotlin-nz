package com.example.kotlin_nz.data.datasource.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.kotlin_nz.domain.models.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(data: String): Source {
        val parts = data.split(",")
        return Source(
            id = if (parts[0] == "null") null else parts[0],
            name = parts[1]
        )
    }
}