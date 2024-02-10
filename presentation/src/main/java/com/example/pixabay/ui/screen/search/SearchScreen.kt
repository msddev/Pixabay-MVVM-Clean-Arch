package com.example.pixabay.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pixabay.ui.screen.search.components.DetailAlertDialog
import com.example.pixabay.ui.screen.search.components.SearchBarView
import com.example.pixabay.ui.screen.search.components.SearchResultView
import com.example.pixabay.ui.screen.search.components.TopAppBarView

@Composable
internal fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navigateToDetailScreen: (String) -> Unit,
    onUpdateTheme: () -> Unit,
) {
    val imagesPaging = searchViewModel.imagesPaging.collectAsLazyPagingItems()

    var showDetailAlertDialog by remember { mutableStateOf(false) }
    var imageId by remember { mutableStateOf("") }

    var showSearchView by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            SearchBarView(
                visibility = showSearchView,
                onSearchClick = searchViewModel::onSearch,
                onBackClick = {
                    showSearchView = false
                }
            )

            TopAppBarView(
                visibility = showSearchView.not(),
                isDarkMode = false,
                onSearchClick = {
                    showSearchView = true
                },
                onThemeClick = onUpdateTheme
            )
        }
    ) { padding ->
        SearchResultView(
            parentPadding = padding,
            imagesPaging = imagesPaging,
            onItemClick = { id ->
                imageId = id
                showDetailAlertDialog = true
            }
        )
    }

    DetailAlertDialog(
        show = showDetailAlertDialog,
        onDismissRequest = {
            showDetailAlertDialog = false
        },
        onConfirmation = {
            showDetailAlertDialog = false
            navigateToDetailScreen.invoke(imageId)
        }
    )
}
