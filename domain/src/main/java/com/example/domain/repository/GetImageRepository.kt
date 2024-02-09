package com.example.domain.repository

import com.example.domain.model.ImageDomainModel
import com.example.domain.unit.Resource
import kotlinx.coroutines.flow.Flow

interface GetImageRepository {
    fun getImage(imageId: String): Flow<Resource<ImageDomainModel>>
}