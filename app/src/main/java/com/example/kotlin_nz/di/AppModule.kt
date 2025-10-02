package com.example.kotlin_nz.di

import android.app.Application
import com.example.kotlin_nz.data.manager.LocalUserManagerImpl
import com.example.kotlin_nz.domain.manager.LocalUserManager
import com.example.kotlin_nz.domain.usecases.AppEntryUseCases
import com.example.kotlin_nz.domain.usecases.ReadAppEntry
import com.example.kotlin_nz.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideReadAppEntryUseCase(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}