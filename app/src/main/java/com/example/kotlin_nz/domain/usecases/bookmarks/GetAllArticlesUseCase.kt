package com.example.kotlin_nz.domain.usecases.bookmarks

import com.example.kotlin_nz.domain.repository.NewsBookmarkRepository
import javax.inject.Inject

class GetAllArticlesUseCase @Inject constructor(
    private val newsBookmarkRepository: NewsBookmarkRepository
) {
    operator fun invoke() = newsBookmarkRepository.getAllArticles()
}