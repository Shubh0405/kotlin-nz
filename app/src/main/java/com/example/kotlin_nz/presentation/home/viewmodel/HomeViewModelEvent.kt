package com.example.kotlin_nz.presentation.home.viewmodel

sealed class HomeViewModelEvent {
    object Refresh: HomeViewModelEvent()
}