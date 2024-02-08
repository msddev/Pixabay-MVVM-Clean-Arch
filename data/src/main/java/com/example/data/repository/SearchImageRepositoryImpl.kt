package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.dataSource.local.dao.ImageDao
import com.example.data.dataSource.local.dao.RemoteKeyDao
import com.example.data.dataSource.remote.api.ImageApi
import com.example.data.mapper.toDomainImage
import com.example.data.mediator.ImageRemoteMediator
import com.example.data.util.DataConstants
import com.example.domain.model.ImageDomainModel
import com.example.domain.repository.SearchImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class SearchImageRepositoryImpl(
    private val imageApi: ImageApi,
    private val imageDao: ImageDao,
    private val remoteKeyDao: RemoteKeyDao,
) : SearchImageRepository {

    override fun searchImage(searchString: String): Flow<PagingData<ImageDomainModel>> {
        val pagingSourceFactory = { imageDao.queryImages(searchString) }

        return Pager(
            config = PagingConfig(
                pageSize = DataConstants.PAGE_SIZE,
                maxSize = DataConstants.PAGE_SIZE + (DataConstants.PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            remoteMediator = ImageRemoteMediator(
                query = searchString,
                imageApi = imageApi,
                imageDao = imageDao,
                remoteKeyDao = remoteKeyDao,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { imageEntity ->
                imageEntity.toDomainImage()
            }
        }
    }
}