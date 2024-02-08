package com.example.pixabay.ui.screen.search

import android.content.res.Configuration
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pixabay.ui.theme.PixabayTheme

@Composable
internal fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
) {


    Log.d("TAGTAG", "result: ${searchViewModel.state.collectAsStateWithLifecycle().value}")
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        SearchScreen()
    }
}