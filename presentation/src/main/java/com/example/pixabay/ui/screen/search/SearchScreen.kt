package com.example.pixabay.ui.screen.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val listState: LazyListState = rememberLazyListState()

    val imagesPaging = searchViewModel.imagesPaging.collectAsLazyPagingItems()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
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

        when (imagesPaging.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(8.dp),
                            color = DarkBlue
                        )
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = stringResource(id = R.string.error_paging_images),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }
            }

            is LoadState.NotLoading -> {

            }
        }
    }

    when (imagesPaging.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingColumn(title = stringResource(id = R.string.searching_image))
        }

        is LoadState.Error -> {
            RetryColumn(
                message = stringResource(id = R.string.error_loading_images),
                onRetry = {
                    imagesPaging.refresh()
                }
            )
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