package com.example.kotlin_nz.domain.usecases

import com.example.kotlin_nz.domain.manager.LocalUserManager

class SaveAppEntry(
    private val LocalUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        LocalUserManager.saveAppEntry()
    }
}