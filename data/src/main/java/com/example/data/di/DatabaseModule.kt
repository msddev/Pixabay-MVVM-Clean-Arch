package com.example.data.di

import android.content.Context
import com.example.data.dataSource.local.database.PixabayRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        PixabayRoomDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideImageDao(database: PixabayRoomDatabase) =
        database.imageDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(database: PixabayRoomDatabase) =
        database.remoteKeyDao()
}