package com.example.kotlin_nz.presentation.nvgraph

sealed class Routes (
    val route: String
) {
    object OnboardingScreen: Routes(route = "onboarding_screen")
    object HomeScreen: Routes(route = "home_screen")
    object DetailsScreen: Routes(route = "details_screen")
    object SearchScreen: Routes(route = "search_screen")
    object BookmarksScreen: Routes(route = "bookmarks_screen")
    object AppStartNavigation: Routes(route = "app_start_navigation")
    object NewsNavigation: Routes(route = "news_navigation")
    object NewsNavigatorScreen: Routes(route = "news_navigation_screen")
}