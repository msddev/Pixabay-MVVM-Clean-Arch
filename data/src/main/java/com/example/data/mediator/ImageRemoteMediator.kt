package com.example.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.dataSource.local.dao.ImageDao
import com.example.data.dataSource.local.dao.RemoteKeyDao
import com.example.data.dataSource.local.entity.ImageEntity
import com.example.data.dataSource.local.entity.RemoteKeyEntity
import com.example.data.dataSource.remote.api.ImageApi
import com.example.data.mapper.toImageEntity
import com.example.data.util.DataConstants
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val query: String,
    private val imageApi: ImageApi,
    private val imageDao: ImageDao,
    private val remoteKeyDao: RemoteKeyDao,
) : RemoteMediator<Int, ImageEntity>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: DataConstants.FIRST_PAGE
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val imagesResponse = imageApi.searchImage(
                searchString = query,
                page = page,
                perPage = DataConstants.PAGE_SIZE
            )
            val images = imagesResponse.images
            val endOfPaginationReached = images.isEmpty()

            if (loadType == LoadType.REFRESH) {
                remoteKeyDao.clearRemoteKeys()
                imageDao.clearAll()
            }
            val prevPage = if (page == DataConstants.FIRST_PAGE) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1
            val keys = images.map {
                RemoteKeyEntity(imageId = it.id, prevPage = prevPage, nextPage = nextPage)
            }
            remoteKeyDao.insertAll(keys)
            imageDao.insertAll(
                images.map { imageDto ->
                    imageDto.toImageEntity(searchSting = query)
                }
            )

            return MediatorResult.Success(endOfPaginationReached = false)

        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ImageEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                remoteKeyDao.remoteKeysImageId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ImageEntity>): RemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                remoteKeyDao.remoteKeysImageId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ImageEntity>
    ): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                remoteKeyDao.remoteKeysImageId(repoId)
            }
        }
    }
}