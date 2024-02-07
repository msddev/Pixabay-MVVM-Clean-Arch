package com.example.data.repository

import androidx.paging.PagingData
import com.example.domain.model.ImageModel
import com.example.domain.repository.SearchImageRepository
import kotlinx.coroutines.flow.Flow

class SearchImageRepositoryImpl : SearchImageRepository {
    override fun searchImage(searchString: String): Flow<PagingData<ImageModel>> {
        TODO("Not yet implemented")
    }
}