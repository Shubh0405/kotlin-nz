package com.example.kotlin_nz.presentation.details.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.ImageResult
import com.example.kotlin_nz.R
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.models.Source
import com.example.kotlin_nz.presentation.Dimens
import com.example.kotlin_nz.presentation.details.viewmodel.DetailViewModelEvent
import com.example.kotlin_nz.presentation.details.viewmodel.DetailsViewModel

@Composable
fun DetailScreen(
    article: Article,
    onNavigationPop: () -> Unit
) {

    val detailViewModel: DetailsViewModel = hiltViewModel()

    LaunchedEffect(article) {
        detailViewModel.checkIfArticleIsBookmarked(article.url)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.SmallPadding1, horizontal = Dimens.SmallPadding1),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onNavigationPop()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "arrow_back",
                    modifier = Modifier.size(Dimens.bottomBarIconSize)
                )
            }
            IconButton(
                onClick = { detailViewModel.onEvent(DetailViewModelEvent.ToggleBookmarkEvent(article = article)) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_bookmark),
                    contentDescription = "bookmark",
                    modifier = Modifier.size(Dimens.bottomBarIconSize),
                    tint = if(detailViewModel.isArticleBookmarked) colorResource(R.color.input_background) else colorResource(R.color.text_title),
                )
            }
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.urlToImage)
                .crossfade(true)
                .setHeader("User-Agent", "Mozilla/5.0")
                .listener(
                    onError = { request: ImageRequest, result: ImageResult ->
                        if (result is ErrorResult) {
                            Log.d(
                                "ArticleCard",
                                "Image request failed ${request.data} : ${result.throwable.message}"
                            )
                        }
                        // Log the error or handle it as needed
                    }
                )
                .build(),
            contentDescription = "Article Image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f),
            placeholder = painterResource(R.drawable.ic_search),
            error = painterResource(R.drawable.ic_close),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        Column(
            modifier = Modifier.padding(horizontal = Dimens.SmallPadding2)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = colorResource(R.color.text_title)
            )
            Spacer(modifier = Modifier.height(Dimens.SmallPadding3))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "By ${article.author}",
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(R.color.text_medium)
                )
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(R.color.text_medium)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
            Text(
                text = article.content,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.body)
            )
        }

    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {
    DetailScreen(
        article = Article(
            author = "John Doe",
            content = "This is a sample article content.",
            description = "This is a sample article description.",
            publishedAt = "2024-06-01T12:00:00Z",
            source = Source(
                id = "bbc-news",
                name = "BBC News"
            ),
            title = "Sample Article Title",
            url = "https://www.example.com/sample-article",
            urlToImage = "https://s.yimg.com/ny/api/res/1.2/NO2ZJ7I.MZbY8oxLQQHc.A--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD02NzU-/https://s.yimg.com/os/creatr-uploaded-images/2025-09/39261eb0-8cee-11f0-ab6c-4631c2d19e69"
        ),
        onNavigationPop = {}
    )
}