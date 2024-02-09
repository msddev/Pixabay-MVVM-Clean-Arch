package com.example.pixabay.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pixabay.R
import com.example.pixabay.ui.commonComponent.error.RetryColumn
import com.example.pixabay.ui.commonComponent.loading.LoadingColumn
import com.example.pixabay.ui.screen.detail.components.ImageDetailBody

@Composable
internal fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    imageId: String,
    onBackClick: () -> Unit,
) {
    when (val uiState = detailViewModel.state.collectAsStateWithLifecycle().value) {
        DetailUiState.Loading -> {
            LoadingColumn(
                title = stringResource(id = R.string.fetching_image_detail)
            )
        }

        is DetailUiState.Success -> {
            uiState.data?.let { image ->
                ImageDetailBody(
                    imageDetail = image,
                    onBackClick = onBackClick
                )
            }
        }

        is DetailUiState.Error -> {
            RetryColumn(
                message = uiState.message,
                onRetry = {
                    detailViewModel.getImage(imageId)
                }
            )
        }
    }
}