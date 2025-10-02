package com.example.kotlin_nz.domain.usecases

import com.example.kotlin_nz.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val LocalUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return LocalUserManager.readAppEntry()
    }
}