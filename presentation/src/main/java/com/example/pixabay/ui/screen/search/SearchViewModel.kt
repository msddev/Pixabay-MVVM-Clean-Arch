package com.example.pixabay.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.SearchImageUseCase
import com.example.pixabay.mapper.toImagePresentation
import com.example.pixabay.model.ImagePresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchImageUseCase: SearchImageUseCase,
) : ViewModel() {

    val imagesPaging: Flow<PagingData<ImagePresentationModel>> = searchImageUseCase(
        payload = "fruits"
    ).map { pagingData ->
        pagingData.map { imageDomainModel ->
            imageDomainModel.toImagePresentation()
        }
    }.cachedIn(viewModelScope)
}