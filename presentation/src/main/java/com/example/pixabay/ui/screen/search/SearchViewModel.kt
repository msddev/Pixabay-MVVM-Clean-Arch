package com.example.pixabay.ui.screen.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.SearchImageUseCase
import com.example.pixabay.mapper.toImagePresentation
import com.example.pixabay.model.ImagePresentationModel
import com.example.pixabay.util.PresentationConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SearchViewModel @Inject constructor(
    searchImageUseCase: SearchImageUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    fun onSearch(query: String) {
        savedStateHandle[PresentationConstants.KEY_SEARCH_QUERY] = query
    }

    var imagesPaging: Flow<PagingData<ImagePresentationModel>> =
        savedStateHandle.getStateFlow(
            PresentationConstants.KEY_SEARCH_QUERY,
            PresentationConstants.DEFAULT_SEARCH_QUERY
        )
            .debounce(500)
            .filter { it.isNotEmpty() }
            .flatMapLatest { query ->
                searchImageUseCase(
                    payload = query
                ).map { pagingData ->
                    pagingData.map { imageDomainModel ->
                        imageDomainModel.toImagePresentation()
                    }
                }
            }
            .cachedIn(viewModelScope)
}