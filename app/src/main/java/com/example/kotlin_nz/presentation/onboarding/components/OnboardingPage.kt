package com.example.kotlin_nz.presentation.onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_nz.R
import com.example.kotlin_nz.presentation.Dimens
import com.example.kotlin_nz.presentation.Page
import com.example.kotlin_nz.presentation.pages
import com.example.kotlin_nz.ui.theme.KotlinnzTheme

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {

    Column{
        Image(
            modifier = modifier.fillMaxWidth().fillMaxHeight(fraction = 0.6f),
            painter = painterResource(page.image),
            contentDescription = page.description,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(Dimens.MediumPadding1))
        Text(
            text = page.title,
            modifier = modifier.padding(horizontal = Dimens.MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.display_small)
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding2))
        Text(
            text = page.description,
            modifier = modifier.padding(horizontal = Dimens.MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.text_medium)
        )
    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnboardingPagePreview() {
     KotlinnzTheme {
         OnboardingPage(
             page = pages[0]
         )
     }
}