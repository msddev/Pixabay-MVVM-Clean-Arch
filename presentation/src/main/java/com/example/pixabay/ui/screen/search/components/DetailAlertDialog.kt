package com.example.pixabay.ui.screen.search.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.ui.theme.PixabayTheme

@Composable
internal fun DetailAlertDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    if (show.not()) {
        return
    }

    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Filled.Info, contentDescription = null)
        },
        text = {
            Text(
                text = stringResource(R.string.are_you_sure_you_want_to_see_more_details)
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(R.string.dismiss))
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        DetailAlertDialog(
            show = true,
            onConfirmation = {},
            onDismissRequest = {}
        )
    }
}