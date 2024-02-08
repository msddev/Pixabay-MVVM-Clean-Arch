package com.example.pixabay.ui.screen.search

import androidx.paging.PagingData
import com.example.pixabay.model.ImagePresentationModel


sealed class SearchUiState {
    data object Loading : SearchUiState()
    data class Success(
        val data: PagingData<ImagePresentationModel> = PagingData.empty()
    ) : SearchUiState()

    data class Error(val message: String = "") : SearchUiState()
}