package com.example.kotlin_nz.domain.usecases.bookmarks

import com.example.kotlin_nz.domain.repository.NewsBookmarkRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val newsBookmarkRepository: NewsBookmarkRepository
) {
    operator fun invoke(url: String) = newsBookmarkRepository.getArticleByUrl(url)
}