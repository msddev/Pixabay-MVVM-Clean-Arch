package com.example.pixabay.ui.screen.detail

import com.example.pixabay.model.ImagePresentationModel

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val data: ImagePresentationModel? = null) : DetailUiState()
    data class Error(val message: String = "") : DetailUiState()
}