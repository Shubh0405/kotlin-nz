package com.example.kotlin_nz.domain.repository

import com.example.kotlin_nz.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsBookmarkRepository {
    suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getAllArticles(): Flow<List<Article>>

    fun getArticleByUrl(url: String): Flow<Article?>
}