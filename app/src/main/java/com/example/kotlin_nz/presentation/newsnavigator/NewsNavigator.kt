package com.example.kotlin_nz.presentation.newsnavigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_nz.R
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.presentation.bookmark.screen.BookmarkScreen
import com.example.kotlin_nz.presentation.details.screen.DetailScreen
import com.example.kotlin_nz.presentation.home.screens.HomeScreen
import com.example.kotlin_nz.presentation.newsnavigator.components.BottomBarItem
import com.example.kotlin_nz.presentation.newsnavigator.components.NewsBottomBar
import com.example.kotlin_nz.presentation.nvgraph.Routes

@Composable
fun NewsNavigator(
) {

    val navController = rememberNavController()

    var selectedTab by rememberSaveable {
        mutableIntStateOf(0)
    }

    val bottomBarItems = listOf(
        BottomBarItem(
            name = "Home",
            icon = R.drawable.ic_home,
            onClick = {
                onSwitchBottomTab(navController, Routes.HomeScreen)
                selectedTab = 0
            }
        ),
        BottomBarItem(
            name = "Bookmarks",
            icon = R.drawable.ic_bookmark,
            onClick = {
                onSwitchBottomTab(navController, Routes.BookmarksScreen)
                selectedTab = 1
            }
        )
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .navigationBarsPadding(),
        bottomBar = {
            NewsBottomBar(
                itemList = bottomBarItems,
                selectedItem = selectedTab
            )
        }
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues)

        NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
            composable(
                route = Routes.HomeScreen.route
            ) {
                HomeScreen(modifier = modifier, onArticleCardClick = { article ->
                    navigateToDetailsScreen(navController, article)
                })
            }

            composable(
                route = Routes.BookmarksScreen.route
            ) {
                BookmarkScreen()
            }

            composable(
                route = Routes.DetailsScreen.route
            ) {
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailScreen(article = article, onNavigationPop = {
                            navController.navigateUp()
                        })
                    }
            }
        }
    }

}

private fun onSwitchBottomTab(navController: NavHostController, route: Routes) {
    navController.navigate(route.route) {
        popUpTo(Routes.HomeScreen.route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetailsScreen(navController: NavHostController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Routes.DetailsScreen.route)
}