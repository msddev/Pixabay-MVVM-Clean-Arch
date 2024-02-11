package com.example.pixabay.repository

import com.example.domain.model.ImageDomainModel
import com.example.domain.repository.ImageRepository
import com.example.domain.unit.Resource
import com.example.pixabay.model.MockImageDomainModelUtils
import com.example.pixabay.ui.screen.detail.DetailUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeImageRepository(private val state: DetailUiState) : ImageRepository {
    private val mockImage = MockImageDomainModelUtils.getMockImageDomainModel()
    override fun getImage(imageId: String): Flow<Resource<ImageDomainModel>> {
        return when (state) {
            is DetailUiState.Loading -> {
                flowOf(Resource.Loading())
            }

            is DetailUiState.Success -> {
                flowOf(Resource.Success(mockImage))
            }

            is DetailUiState.Error -> {
                flowOf(Resource.Error("ERROR"))
            }
        }
    }

}