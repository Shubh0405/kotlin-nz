package com.example.kotlin_nz.presentation.onboarding.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kotlin_nz.presentation.Dimens
import com.example.kotlin_nz.presentation.common.NewsButton
import com.example.kotlin_nz.presentation.common.NewsTextButton
import com.example.kotlin_nz.presentation.onboarding.components.OnboardingPage
import com.example.kotlin_nz.presentation.onboarding.components.PageIndicator
import com.example.kotlin_nz.presentation.onboarding.viewmodel.OnboardingEvent
import com.example.kotlin_nz.presentation.pages
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    event: (OnboardingEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        HorizontalPager(state = pagerState) { index ->
            OnboardingPage(page = pages[index])
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.wrapContentWidth(),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )
            Row {
                val scope = rememberCoroutineScope()

                if (pagerState.currentPage != 0) NewsTextButton(text = "Back") {
                    scope.launch {
                        pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                    }
                }
                NewsButton(text = if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next") {
                    if (pagerState.currentPage == pages.size - 1) {
                        event(OnboardingEvent.saveAppEntry)
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }

    }
}