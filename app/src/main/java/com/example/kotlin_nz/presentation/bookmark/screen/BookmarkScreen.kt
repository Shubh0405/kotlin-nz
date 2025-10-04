package com.example.kotlin_nz.presentation.bookmark.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.kotlin_nz.R
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.presentation.bookmark.viewmodel.BookmarkViewModel
import com.example.kotlin_nz.presentation.common.ArticleCard

@Composable
fun BookmarkScreen(
    onArticleCardClick: (Article) -> Unit
) {

    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()

    val savedArticles: List<Article>? = bookmarkViewModel.savedArticles

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .navigationBarsPadding()
    ) {
        savedArticles?.let {
            LazyColumn {
                items(savedArticles.size) { index ->
                    val article: Article = savedArticles[index]
                    ArticleCard(
                        modifier = Modifier.clickable { onArticleCardClick(article) },
                        article = article
                    )
                }
            }
        }
    }

}