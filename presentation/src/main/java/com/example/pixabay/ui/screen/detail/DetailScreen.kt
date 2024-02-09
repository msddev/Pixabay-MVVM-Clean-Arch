package com.example.pixabay.ui.screen.detail

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pixabay.R
import com.example.pixabay.ui.commonComponent.error.RetryColumn
import com.example.pixabay.ui.commonComponent.loading.LoadingColumn
import com.example.pixabay.ui.screen.detail.components.ImageDetailContent
import com.example.pixabay.ui.theme.PixabayTheme

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
                ImageDetailContent(
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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        DetailScreen(
            imageId = "1",
            onBackClick = {}
        )
    }
}