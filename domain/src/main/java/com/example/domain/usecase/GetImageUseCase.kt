package com.example.domain.usecase

import com.example.domain.model.ImageDomainModel
import com.example.domain.repository.ImageRepository
import com.example.domain.unit.Resource
import kotlinx.coroutines.flow.Flow

class GetImageUseCase(
    private val getImageRepository: ImageRepository
) {
    operator fun invoke(payload: String): Flow<Resource<ImageDomainModel>> {
        return getImageRepository.getImage(imageId = payload)
    }
}