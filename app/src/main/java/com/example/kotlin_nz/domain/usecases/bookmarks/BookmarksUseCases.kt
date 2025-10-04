package com.example.kotlin_nz.domain.usecases.bookmarks

data class BookmarksUseCases(
    val getAllArticlesUseCase: GetAllArticlesUseCase,
    val getArticleUseCase: GetArticleUseCase,
    val insertDeleteArticleUseCase: InsertDeleteArticleUseCase
)
