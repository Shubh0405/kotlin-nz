package com.example.kotlin_nz.data.repository

import com.example.kotlin_nz.data.datasource.remote.NewsApi
import com.example.kotlin_nz.data.dto.NewsResponse
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.repository.NewsRepository
import com.example.kotlin_nz.utils.ApiResponse
import com.example.kotlin_nz.utils.Constants
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getNews(
        query: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): ApiResponse<List<Article>> {
        try {
            val newsResponse: NewsResponse = newsApi.getNews(apiKey = apiKey, query = query, page = page, pageSize = pageSize)
            return if (newsResponse.status == "ok") {
                ApiResponse.Success(data = newsResponse.articles ?: emptyList())
            } else {
                ApiResponse.Failure(message = newsResponse.message ?: "Unknown error occurred")
            }
        } catch (e: Exception) {
            return ApiResponse.Failure(message = "Some error occurred: ${e.localizedMessage}")
        }
    }
}