package com.example.kotlin_nz.presentation.bookmark.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.usecases.bookmarks.BookmarksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarksUseCases: BookmarksUseCases
): ViewModel() {

    var savedArticles by mutableStateOf<List<Article>?>(null)
        private set

    init {
        getAllSavedArticles()
    }

    private fun getAllSavedArticles() {
        viewModelScope.launch {
            bookmarksUseCases.getAllArticlesUseCase().collectLatest {
                savedArticles = it
            }
        }
    }

}