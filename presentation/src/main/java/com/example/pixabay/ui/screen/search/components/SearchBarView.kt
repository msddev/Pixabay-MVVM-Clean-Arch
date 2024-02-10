package com.example.pixabay.ui.screen.search.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.ui.theme.PixabayTheme
import com.example.pixabay.util.PresentationConstants

@Composable
internal fun SearchBarView(
    visibility: Boolean,
    onSearchClick: (query: String) -> Unit,
    onBackClick: () -> Unit
) {
    var query by remember { mutableStateOf(PresentationConstants.DEFAULT_SEARCH_QUERY) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val queryMaxLength = 25

    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(animationSpec = tween(400)),
        exit = fadeOut(animationSpec = tween(400)),
    ) {
        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_x_small)),
                shape = CircleShape,
                placeholder = {
                    Text(text = stringResource(R.string.search))
                },
                value = query,
                onValueChange = {
                    if (it.length <= queryMaxLength) {
                        query = it
                    }
                },
                leadingIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (query.isEmpty()) {
                                onBackClick.invoke()
                            } else {
                                query = ""
                            }
                        }
                    ) {
                        Icon(
                            imageVector = getIcon(query),
                            contentDescription = null
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()
                    onSearchClick.invoke(query)
                }),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    selectionColors = TextSelectionColors(
                        MaterialTheme.colorScheme.onPrimary,
                        MaterialTheme.colorScheme.onSecondary
                    ),
                )

            )
        }
    }
}

@Composable
private fun getIcon(query: String): ImageVector {
    return if (query.isEmpty()) {
        Icons.AutoMirrored.Filled.ArrowForward
    } else {
        Icons.Filled.Clear
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        Surface {
            SearchBarView(
                visibility = true,
                onSearchClick = {},
                onBackClick = {}
            )
        }
    }
}