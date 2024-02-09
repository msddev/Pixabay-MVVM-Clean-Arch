package com.example.pixabay.ui.screen.search

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pixabay.ui.screen.search.components.SearchBarView
import com.example.pixabay.ui.screen.search.components.SearchResultView

@Composable
internal fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navigateToDetailScreen: (String) -> Unit,
) {
    val activity = (LocalContext.current as? Activity)

    val imagesPaging = searchViewModel.imagesPaging.collectAsLazyPagingItems()

    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            SearchBarView(
                onQueryChange = searchViewModel::onSearch,
                onBackClick = {
                    activity?.finish()
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            SearchResultView(
                imagesPaging = imagesPaging,
                onItemClick = { imageId ->
                    navigateToDetailScreen.invoke(imageId)
                }
            )
        }
    }
}
