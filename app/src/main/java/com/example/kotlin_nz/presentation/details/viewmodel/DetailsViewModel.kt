package com.example.kotlin_nz.presentation.details.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.usecases.bookmarks.BookmarksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val bookmarksUseCases: BookmarksUseCases
) : ViewModel() {
    var isArticleBookmarked by mutableStateOf(false)
        private set

    fun onEvent(event: DetailViewModelEvent) {
        when (event) {
            is DetailViewModelEvent.ToggleBookmarkEvent -> {
                bookmarkArticle(
                    article = event.article
                )
            }
        }
    }

    private fun bookmarkArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarksUseCases.insertDeleteArticleUseCase(article)
        }
    }

    fun checkIfArticleIsBookmarked(url: String) {
        viewModelScope.launch {
            bookmarksUseCases.getArticleUseCase(url).collect { article ->
                isArticleBookmarked = article != null
            }
        }
    }
}