package com.example.data.repository

import com.example.data.dataSource.local.dao.ImageDao
import com.example.data.mapper.toDomainImage
import com.example.data.util.handleException
import com.example.domain.model.ImageDomainModel
import com.example.domain.repository.GetImageRepository
import com.example.domain.unit.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetImageRepositoryImpl(
    private val imageDao: ImageDao,
) : GetImageRepository {
    override fun getImage(imageId: String): Flow<Resource<ImageDomainModel>> = flow {
        emit(Resource.Loading())
        try {
            // single source of truth we will emit data from db only and not directly from remote
            emit(Resource.Success(getImageWithIdFromDb(imageId = imageId)))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.handleException()))
        }
    }

    private suspend fun getImageWithIdFromDb(
        imageId: String,
    ): ImageDomainModel {
        return imageDao.getImagesById(id = imageId).toDomainImage()
    }
}