package com.example.kotlin_nz.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kotlin_nz.R
import com.example.kotlin_nz.presentation.Dimens

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(Dimens.buttonRoundedCornerRadius)
            )
            .clip(RoundedCornerShape(Dimens.buttonRoundedCornerRadius))
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                vertical = Dimens.buttonVerticalPadding,
                horizontal = Dimens.buttonHorizontalPadding
            )
            .clickable(onClick = onClick),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.text_medium),
            )
        )
    }

}