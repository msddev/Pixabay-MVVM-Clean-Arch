package com.example.pixabay.ui.screen.detail

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.ui.commonComponent.loading.LoadingColumn
import com.example.pixabay.ui.theme.PixabayTheme

@Composable
internal fun DetailScreen(
    imageId: String,
    onBackClick: () -> Unit,
) {
    LoadingColumn("Loading")
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