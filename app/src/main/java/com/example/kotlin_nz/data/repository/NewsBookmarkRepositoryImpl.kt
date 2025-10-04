package com.example.kotlin_nz.data.repository

import com.example.kotlin_nz.data.datasource.local.NewsDao
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.repository.NewsBookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsBookmarkRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
): NewsBookmarkRepository {
    override suspend fun insertArticle(article: Article) {
        return newsDao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        return newsDao.deleteArticle(article)
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return newsDao.getAllArticles()
    }

    override fun getArticleByUrl(url: String): Flow<Article?> {
        return newsDao.getArticleByUrl(url)
    }
}