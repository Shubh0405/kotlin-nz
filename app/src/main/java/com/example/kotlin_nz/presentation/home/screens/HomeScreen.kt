package com.example.kotlin_nz.presentation.home.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.kotlin_nz.R
import com.example.kotlin_nz.presentation.common.ArticleCard
import com.example.kotlin_nz.presentation.home.viewmodel.ArticleState
import com.example.kotlin_nz.presentation.home.viewmodel.HomeViewModel
import com.example.kotlin_nz.presentation.home.viewmodel.HomeViewModelEvent

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = hiltViewModel()

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1 }
            .collect { lastIndex ->
                val total = homeViewModel.articleState.articles.size

                if (lastIndex >= total - 2 && !homeViewModel.articleState.isLoading && !homeViewModel.articleState.error.isNotBlank()) {
                    homeViewModel.onEvent(HomeViewModelEvent.Refresh)
                }
            }
    }

    Column {
        LazyColumn(state = listState) {
            items(homeViewModel.articleState.articles.size) { index ->
                val article = homeViewModel.articleState.articles[index]
                ArticleCard(
                    article = article
                )
            }
            if (homeViewModel.articleState.isLoading) {
                item {
                    CircularProgressIndicator()
                }
            }

            if (homeViewModel.articleState.error.isNotBlank()) {
                item {
                    Text(
                        "Error : ${homeViewModel.articleState.error}", style = TextStyle(
                            color = colorResource(R.color.text_medium)
                        )
                    )
                }
            }
        }
    }

}