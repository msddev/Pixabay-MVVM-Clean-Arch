package com.example.pixabay.ui.screen.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pixabay.ui.screen.search.components.SearchResultItem
import com.example.pixabay.ui.theme.PixabayTheme

@Composable
internal fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
) {

    val images = searchViewModel.searchImages("fruits").collectAsLazyPagingItems()


    /*LaunchedEffect(key1 = moviesPaging.loadState) {
        viewModel.onLoadStateUpdate(moviesPaging.loadState)
    }*/

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = images.itemCount,
        ) { index ->
            SearchResultItem(
                thumbnail = images[index]?.previewImageURL.orEmpty(),
                username = images[index]?.user.orEmpty(),
                tags = images[index]?.tags.orEmpty(),
                onItemClick = {

                }
            )
        }

        when (val state = images.loadState.refresh) { // FIRST LOAD
            is LoadState.Error -> {
                //TODO Error Item
                //state.error to get error message
            }

            is LoadState.Loading -> { // Loading UI
                item {

                }
            }

            is LoadState.NotLoading -> {

            }
        }

        when (val state = images.loadState.append) { // Pagination
            is LoadState.Error -> {
                //TODO Pagination Error Item
                //state.error to get error message
            }

            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            is LoadState.NotLoading -> {

            }
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