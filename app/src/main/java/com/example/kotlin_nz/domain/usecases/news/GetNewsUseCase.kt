package com.example.kotlin_nz.domain.usecases.news


import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.repository.NewsRepository
import com.example.kotlin_nz.utils.ApiResponse
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int,
        pageSize: Int
    ): ApiResponse<List<Article>> {
        return newsRepository.getNews(
            query = query,
            page = page,
            pageSize = pageSize
        )
    }
}