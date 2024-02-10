package com.example.pixabay.ui.screen.search.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.ui.theme.PixabayTheme
import com.example.pixabay.util.textSp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBarView(
    isDarkMode: Boolean,
    onSearchClick: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Surface(shadowElevation = dimensionResource(id = R.dimen.top_app_bar_elevation)) {
        TopAppBar(
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = dimensionResource(id = R.dimen.padding_small)),
                    text = stringResource(R.string.app_name),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = dimensionResource(id = R.dimen.text_size_x_large).textSp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            actions = {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
                IconButton(onClick = onThemeClick) {
                    val painter = if (isDarkMode) {
                        R.drawable.baseline_dark_mode_24
                    } else {
                        R.drawable.outline_dark_mode_24
                    }
                    Icon(
                        painter = painterResource(id = painter),
                        contentDescription = null
                    )
                }
            },
        )
    }
}

@Preview(locale = "en", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(locale = "en", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        SearchTopAppBarView(
            isDarkMode = true,
            onSearchClick = {

            },
            onThemeClick = {

            }
        )
    }
}