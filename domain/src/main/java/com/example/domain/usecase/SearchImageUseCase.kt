package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.ImageDomainModel
import com.example.domain.repository.SearchImageRepository
import kotlinx.coroutines.flow.Flow

class SearchImageUseCase(
    private val searchImagesRepository: SearchImageRepository
) {
    operator fun invoke(payload: String): Flow<PagingData<ImageDomainModel>> {
        return searchImagesRepository.searchImage(payload)
    }
}