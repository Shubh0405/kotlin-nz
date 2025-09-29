package com.example.kotlin_nz.presentation.onboarding.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kotlin_nz.presentation.onboarding.components.OnboardingPage
import com.example.kotlin_nz.presentation.pages

@Composable
fun OnboardingScreen() {
    Column (modifier = Modifier.fillMaxSize()) {

        val pagerState = rememberPagerState (initialPage = 0) {
            pages.size
        }

        HorizontalPager(state = pagerState) { index ->
            OnboardingPage(page = pages[index])
        }

    }
}