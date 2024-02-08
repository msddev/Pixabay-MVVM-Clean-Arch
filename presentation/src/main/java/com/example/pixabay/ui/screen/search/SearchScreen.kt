package com.example.pixabay.ui.screen.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pixabay.R
import com.example.pixabay.ui.commonComponent.error.RetryColumn
import com.example.pixabay.ui.commonComponent.loading.LoadingColumn
import com.example.pixabay.ui.screen.search.components.SearchResultItem
import com.example.pixabay.ui.theme.DarkBlue
import com.example.pixabay.ui.theme.PixabayTheme

@Composable
internal fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val imagesPaging = searchViewModel.searchImages("fruits").collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = imagesPaging.itemCount,
        ) { index ->
            val item = imagesPaging[index]
            SearchResultItem(
                thumbnail = item?.previewImageURL.orEmpty(),
                username = item?.user.orEmpty(),
                tags = item?.tags.orEmpty(),
                onItemClick = {

                }
            )
        }
    }

    when (val uiState = imagesPaging.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingColumn(title = stringResource(id = R.string.searching_image))
        }

        is LoadState.Error -> {
            RetryColumn(
                message = uiState.error.message
                    ?: stringResource(id = R.string.error_loading_images),
                onRetry = {
                    imagesPaging.refresh()
                }
            )
        }

        is LoadState.NotLoading -> {

        }
    }

    when (val state = imagesPaging.loadState.append) {
        is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp),
                    color = DarkBlue
                )
            }
        }

        is LoadState.Error -> {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    imagesPaging.refresh()
                }
            ) {
                Text(text = "Paging Retry")
            }
        }

        is LoadState.NotLoading -> {

        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        SearchScreen()
    }
}