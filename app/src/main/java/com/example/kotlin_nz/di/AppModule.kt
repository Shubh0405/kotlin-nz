package com.example.kotlin_nz.di

import android.app.Application
import com.example.kotlin_nz.data.datasource.remote.NewsApi
import com.example.kotlin_nz.data.datasource.remote.RetrofitInstance
import com.example.kotlin_nz.data.manager.LocalUserManagerImpl
import com.example.kotlin_nz.data.repository.NewsRepositoryImpl
import com.example.kotlin_nz.domain.manager.LocalUserManager
import com.example.kotlin_nz.domain.repository.NewsRepository
import com.example.kotlin_nz.domain.usecases.appentry.AppEntryUseCases
import com.example.kotlin_nz.domain.usecases.appentry.ReadAppEntry
import com.example.kotlin_nz.domain.usecases.appentry.SaveAppEntry
import com.example.kotlin_nz.domain.usecases.news.GetNewsUseCase
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

    @Provides
    @Singleton
    fun provideNewsApiInstance(): NewsApi = RetrofitInstance.newsApi

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideGetNewsUseCase(
        newsRepository: NewsRepository
    ): GetNewsUseCase = GetNewsUseCase(newsRepository)
}