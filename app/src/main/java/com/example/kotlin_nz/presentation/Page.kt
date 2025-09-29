package com.example.kotlin_nz.presentation

import androidx.annotation.DrawableRes
import com.example.kotlin_nz.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Welcome to Kotlin NZ",
        description = "Your gateway to the latest news and updates from Manchester United.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Stay Informed",
        description = "Get real-time news updates and stay informed about what's happening around you.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Personalized Experience",
        description = "Customize your news feed and get articles that matter to you.",
        image = R.drawable.onboarding3
    )
)
