package com.example.pixabay.ui.screen.search.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pixabay.R
import com.example.pixabay.model.ImagePresentationModel
import com.example.pixabay.ui.commonComponent.EmptyView
import com.example.pixabay.ui.commonComponent.error.RetryColumn
import com.example.pixabay.ui.commonComponent.loading.LoadingColumn
import com.example.pixabay.ui.theme.PixabayTheme
import com.example.pixabay.util.fadingEdge
import com.example.pixabay.util.textSp
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchResultView(
    imagesPaging: LazyPagingItems<ImagePresentationModel>,
    onItemClick: (item: String) -> Unit,
) {
    val listState: LazyListState = rememberLazyListState()
    val topFade = Brush.verticalGradient(0f to Color.Transparent, 0.01f to Color.White)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fadingEdge(topFade)
            .size(dimensionResource(id = R.dimen.padding_small)),
        state = listState
    ) {
        items(count = imagesPaging.itemCount) { index ->
            imagesPaging[index]?.let { item ->
                SearchResultItemView(
                    thumbnail = item.previewImageURL,
                    username = item.user,
                    tags = item.tags,
                    onItemClick = {
                        onItemClick.invoke(item.id.toString())
                    }
                )
            }
        }

        when (imagesPaging.loadState.append) {
            is LoadState.Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen.padding_small)),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            is LoadState.Error -> {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen.padding_small)),
                        text = stringResource(id = R.string.error_paging_images),
                        textAlign = TextAlign.Center,
                        lineHeight = dimensionResource(id = R.dimen.text_size_large).textSp
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
            if (
                imagesPaging.itemCount == 0 &&
                imagesPaging.loadState.source.refresh != LoadState.Loading
            ) {
                EmptyView(message = stringResource(id = R.string.no_result))
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        SearchResultView(
            imagesPaging = flowOf(
                PagingData.from(
                    listOf(ImagePresentationModel()),
                    sourceLoadStates =
                    LoadStates(
                        refresh = LoadState.NotLoading(true),
                        append = LoadState.NotLoading(true),
                        prepend = LoadState.NotLoading(true),
                    )
                )
            ).collectAsLazyPagingItems(),
            onItemClick = {}
        )
    }
}