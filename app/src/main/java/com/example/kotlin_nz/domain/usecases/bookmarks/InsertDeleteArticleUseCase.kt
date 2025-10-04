package com.example.kotlin_nz.domain.usecases.bookmarks

import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.repository.NewsBookmarkRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class InsertDeleteArticleUseCase @Inject constructor(
    private val newsBookmarkRepository: NewsBookmarkRepository
) {

    suspend operator fun invoke(article: Article) {
        val isBookmarked = newsBookmarkRepository.getArticleByUrl(article.url).first() != null
        if (isBookmarked) {
            newsBookmarkRepository.deleteArticle(article)
        } else {
            newsBookmarkRepository.insertArticle(article)
        }

    }
}