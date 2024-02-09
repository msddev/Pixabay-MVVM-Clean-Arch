package com.example.domain.usecase

import com.example.domain.model.ImageDomainModel
import com.example.domain.repository.GetImageRepository
import com.example.domain.unit.Resource
import kotlinx.coroutines.flow.Flow

class GetImageUseCase(
    private val getImageRepository: GetImageRepository
) {
    operator fun invoke(payload: String): Flow<Resource<ImageDomainModel>> {
        return getImageRepository.getImage(imageId = payload)
    }
}