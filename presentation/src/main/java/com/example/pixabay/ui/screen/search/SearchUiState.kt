package com.example.pixabay.ui.screen.search


sealed class SearchUiState {
    data object Loading : SearchUiState()
    data class Success(val data: List<String> = emptyList()) : SearchUiState()
    data class Error(val message: String = "") : SearchUiState()
}