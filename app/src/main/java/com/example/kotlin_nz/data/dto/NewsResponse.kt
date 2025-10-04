package com.example.kotlin_nz.data.dto

import com.example.kotlin_nz.domain.models.Article

data class NewsResponse(
    val articles: List<Article>?,
    val status: String,
    val totalResults: Int?,
    val message: String?
)