package com.example.kotlin_nz.presentation.common

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.ImageResult
import com.example.kotlin_nz.R
import com.example.kotlin_nz.domain.models.Article
import com.example.kotlin_nz.domain.models.Source
import com.example.kotlin_nz.presentation.Dimens

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.SmallPadding2, vertical = Dimens.MediumPadding1)
    ) {
        Row {
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
                modifier = modifier
                    .fillMaxWidth(fraction = 0.4f)
                    .height(Dimens.articleCardSize)
                    .clip(RoundedCornerShape(Dimens.buttonRoundedCornerRadius)),
                placeholder = painterResource(R.drawable.ic_search),
                error = painterResource(R.drawable.ic_close),
                contentScale = ContentScale.Crop
            )
            Spacer(
                modifier = modifier.width(Dimens.SmallPadding2)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(Dimens.articleCardSize),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.title,
                    style = TextStyle(
                        color = colorResource(R.color.text_title),
                        fontStyle = MaterialTheme.typography.bodyMedium.fontStyle
                    )
                )
                Text(
                    text = article.source.name,
                    style = TextStyle(
                        color = colorResource(R.color.text_medium),
                        fontStyle = MaterialTheme.typography.bodySmall.fontStyle
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    ArticleCard(
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
        )
    )
}