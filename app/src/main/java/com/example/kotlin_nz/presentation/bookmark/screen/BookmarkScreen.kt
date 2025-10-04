package com.example.kotlin_nz.presentation.bookmark.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import com.example.kotlin_nz.R
import com.example.kotlin_nz.domain.models.Article

@Composable
fun BookmarkScreen() {
    Text(
        text = "Bookmark Screen",
        style = TextStyle(
            color = colorResource(R.color.text_title),
            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle
        )
    )
}