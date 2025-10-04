package com.example.kotlin_nz.presentation.details.viewmodel

import com.example.kotlin_nz.domain.models.Article

sealed class DetailViewModelEvent {
    data class ToggleBookmarkEvent(val article: Article) : DetailViewModelEvent()
}