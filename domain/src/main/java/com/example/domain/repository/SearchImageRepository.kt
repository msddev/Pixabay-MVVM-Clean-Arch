package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.ImageDomainModel
import kotlinx.coroutines.flow.Flow

interface SearchImageRepository {
    fun searchImage(searchString: String): Flow<PagingData<ImageDomainModel>>
}