package com.example.kotlin_nz.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_nz.data.datasource.local.NewsDao
import com.example.kotlin_nz.data.datasource.local.NewsDatabase
import com.example.kotlin_nz.data.datasource.local.NewsTypeConverter
import com.example.kotlin_nz.data.datasource.remote.NewsApi
import com.example.kotlin_nz.data.datasource.remote.RetrofitInstance
import com.example.kotlin_nz.data.manager.LocalUserManagerImpl
import com.example.kotlin_nz.data.repository.NewsBookmarkRepositoryImpl
import com.example.kotlin_nz.data.repository.NewsRepositoryImpl
import com.example.kotlin_nz.domain.manager.LocalUserManager
import com.example.kotlin_nz.domain.repository.NewsBookmarkRepository
import com.example.kotlin_nz.domain.repository.NewsRepository
import com.example.kotlin_nz.domain.usecases.appentry.AppEntryUseCases
import com.example.kotlin_nz.domain.usecases.appentry.ReadAppEntry
import com.example.kotlin_nz.domain.usecases.appentry.SaveAppEntry
import com.example.kotlin_nz.domain.usecases.bookmarks.BookmarksUseCases
import com.example.kotlin_nz.domain.usecases.bookmarks.GetAllArticlesUseCase
import com.example.kotlin_nz.domain.usecases.bookmarks.GetArticleUseCase
import com.example.kotlin_nz.domain.usecases.bookmarks.InsertDeleteArticleUseCase
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

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase = Room.databaseBuilder<NewsDatabase>(context = application, name = "news_db")
        .addTypeConverter(
            NewsTypeConverter()
        ).fallbackToDestructiveMigration(false).build()

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao()

    @Provides
    @Singleton
    fun provideNewsBookmarkRepository(
        newsDao: NewsDao
    ): NewsBookmarkRepository = NewsBookmarkRepositoryImpl(newsDao)

    @Provides
    @Singleton
    fun provideNewsBookmarkUseCases(
        newsBookmarkRepository: NewsBookmarkRepository
    ): BookmarksUseCases = BookmarksUseCases(
        getAllArticlesUseCase = GetAllArticlesUseCase(newsBookmarkRepository = newsBookmarkRepository),
        getArticleUseCase = GetArticleUseCase(newsBookmarkRepository = newsBookmarkRepository),
        insertDeleteArticleUseCase = InsertDeleteArticleUseCase(newsBookmarkRepository = newsBookmarkRepository)
    )
}