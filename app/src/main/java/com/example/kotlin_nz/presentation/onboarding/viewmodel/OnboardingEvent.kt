package com.example.kotlin_nz.presentation.onboarding.viewmodel

sealed class OnboardingEvent {
    object saveAppEntry: OnboardingEvent()
}