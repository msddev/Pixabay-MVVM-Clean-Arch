package com.example.pixabay.ui.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.SearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            searchImageUseCase(
                payload = "fruits"
            ).onEach { result ->
                Log.d("TAG", "result: $result")
            }
        }
    }
}