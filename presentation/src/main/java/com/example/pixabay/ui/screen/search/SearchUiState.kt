package com.example.pixabay.ui.screen.search

import com.example.pixabay.model.ImagePresentationModel


sealed class SearchUiState {
    data object Loading : SearchUiState()
    data class Success(
        val data: List<ImagePresentationModel> = listOf()
    ) : SearchUiState()

    data class Error(val message: String = "") : SearchUiState()
}