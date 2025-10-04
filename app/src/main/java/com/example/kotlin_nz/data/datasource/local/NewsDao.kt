package com.example.kotlin_nz.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_nz.domain.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM Article")
    fun getAllArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url = :url")
    fun getArticleByUrl(url: String): Flow<Article?>
}