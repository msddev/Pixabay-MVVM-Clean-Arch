package com.example.pixabay.di

import com.example.data.dataSource.local.dao.ImageDao
import com.example.data.dataSource.local.dao.RemoteKeyDao
import com.example.data.dataSource.remote.api.ImageApi
import com.example.data.repository.SearchImageRepositoryImpl
import com.example.domain.repository.SearchImageRepository
import com.example.domain.usecase.SearchImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    @Singleton
    fun provideSearchImageUseCase(searchImageRepository: SearchImageRepository) =
        SearchImageUseCase(searchImageRepository)

    @Provides
    @Singleton
    fun provideSearchImageRepository(
        imageApi: ImageApi,
        imageDao: ImageDao,
        remoteKeyDao: RemoteKeyDao,
    ): SearchImageRepository = SearchImageRepositoryImpl(
        imageApi = imageApi,
        imageDao = imageDao,
        remoteKeyDao = remoteKeyDao
    )
}