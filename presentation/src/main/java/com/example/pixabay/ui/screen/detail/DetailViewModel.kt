package com.example.pixabay.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.unit.Resource
import com.example.domain.usecase.GetImageUseCase
import com.example.pixabay.mapper.toImagePresentation
import com.example.pixabay.ui.navigation.ARG_IMAGE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val state = _state.asStateFlow()

    init {
        val imageId = savedStateHandle.get<String>(ARG_IMAGE_ID).orEmpty()
        getImage(imageId)
    }

    fun getImage(id: String) {
        viewModelScope.launch {
            getImageUseCase(id).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = DetailUiState.Loading
                    }

                    is Resource.Success -> {
                        _state.value = DetailUiState.Success(
                            data = result.data?.toImagePresentation()
                        )
                    }

                    is Resource.Error -> {
                        _state.value = DetailUiState.Error(
                            message = result.message ?: "Oops! something went wrong."
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}