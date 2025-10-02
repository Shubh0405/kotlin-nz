package com.example.kotlin_nz

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_nz.domain.usecases.AppEntryUseCases
import com.example.kotlin_nz.presentation.nvgraph.NavGraph
import com.example.kotlin_nz.presentation.onboarding.screens.OnboardingScreen
import com.example.kotlin_nz.presentation.onboarding.viewmodel.OnboardingViewModel
import com.example.kotlin_nz.ui.theme.KotlinnzTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    val mainViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition(
            condition = { mainViewModel.splashScreenCondition }
        )
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collectLatest {
                Log.d("TEST", it.toString())
            }
        }

        setContent {
            KotlinnzTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    val startingDestination: String = mainViewModel.startingDestination
                    NavGraph(startDestination = startingDestination)
                }
            }
        }
    }
}