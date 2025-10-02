package com.example.kotlin_nz.presentation.nvgraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_nz.presentation.onboarding.screens.OnboardingScreen
import com.example.kotlin_nz.presentation.onboarding.viewmodel.OnboardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Routes.AppStartNavigation.route,
            startDestination = Routes.OnboardingScreen.route
        ) {
            composable(
                route = Routes.OnboardingScreen.route
            ) {
                val onboardingViewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(event = onboardingViewModel::onEvent)
            }
        }

        navigation(
            route = Routes.NewsNavigation.route,
            startDestination = Routes.NewsNavigatorScreen.route
        ) {
            composable(
                route = Routes.NewsNavigatorScreen.route
            ) {
                Box (
                    modifier = Modifier.fillMaxSize().safeDrawingPadding()
                ) {
                    Text("News Navigator Screen")
                }
            }
        }
    }

}