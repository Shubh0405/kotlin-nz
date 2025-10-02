package com.example.kotlin_nz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_nz.domain.usecases.AppEntryUseCases
import com.example.kotlin_nz.presentation.nvgraph.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashScreenCondition by mutableStateOf(true)
        private set

    var startingDestination by mutableStateOf(Routes.AppStartNavigation.route)
        private set

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collectLatest { onBoardingCompleted ->
                if (onBoardingCompleted) {
                    startingDestination = Routes.NewsNavigation.route
                } else {
                    startingDestination = Routes.AppStartNavigation.route
                }
                delay(300)
                splashScreenCondition = false
            }
        }
    }
}