package com.example.kotlin_nz.domain.repository

import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.utils.ApiResponse
import com.example.kotlin_nz.utils.Constants

interface NewsRepository {
    suspend fun getNews(
        query: String,
        page: Int,
        pageSize: Int,
        apiKey: String = Constants.API_KEY
    ): ApiResponse<List<Article>>
}