package com.example.pixabay.ui.screen.search

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pixabay.ui.screen.search.components.DetailAlertDialog
import com.example.pixabay.ui.screen.search.components.SearchBarView
import com.example.pixabay.ui.screen.search.components.SearchResultView

@Composable
internal fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navigateToDetailScreen: (String) -> Unit,
) {
    val activity = (LocalContext.current as? Activity)

    val imagesPaging = searchViewModel.imagesPaging.collectAsLazyPagingItems()

    var showDetailAlertDialog by remember { mutableStateOf(false) }
    var imageId by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            SearchBarView(
                onSearchClick = searchViewModel::onSearch,
                onBackClick = {
                    activity?.finish()
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            SearchResultView(
                imagesPaging = imagesPaging,
                onItemClick = { id ->
                    imageId = id
                    showDetailAlertDialog = true
                }
            )
        }
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
