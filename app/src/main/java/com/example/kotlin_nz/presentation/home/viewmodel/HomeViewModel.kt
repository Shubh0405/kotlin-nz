package com.example.kotlin_nz.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.usecases.news.GetNewsUseCase
import com.example.kotlin_nz.utils.ApiResponse
import com.example.kotlin_nz.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    var articleState by mutableStateOf(ArticleState(isLoading = true))
        private set

    init {
        onEvent(HomeViewModelEvent.Refresh)
    }

    fun onEvent(event: HomeViewModelEvent) {
        when (event) {
            HomeViewModelEvent.Refresh -> {
                getNews()
            }
        }
    }

    private fun getNews() {
        articleState = articleState.copy(isLoading = true)

        val page: Int = (articleState.articles.size / Constants.PAGE_SIZE) + 1

        viewModelScope.launch {
            val apiResponse: ApiResponse<List<Article>> =
                getNewsUseCase(query = "bitcoin", page = page, pageSize = Constants.PAGE_SIZE)
            when (apiResponse) {
                is ApiResponse.Success -> {

                    val updatedArticle: List<Article> = articleState.articles + apiResponse.data

                    articleState = articleState.copy(
                        isLoading = false,
                        articles = updatedArticle,
                        error = ""
                    )
                }

                is ApiResponse.Failure -> {
                    articleState = articleState.copy(
                        isLoading = false,
                        error = apiResponse.message
                    )
                }
            }
        }
    }

}

data class ArticleState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String = ""
)