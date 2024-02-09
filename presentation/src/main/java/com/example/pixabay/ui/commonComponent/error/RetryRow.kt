package com.example.pixabay.ui.commonComponent.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pixabay.R
import com.example.pixabay.util.textSp

@Composable
fun RetryRow(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size_large)),
        )

        Text(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_standard)
            ),
            text = message,
            textAlign = TextAlign.Center,
            lineHeight = dimensionResource(id = R.dimen.line_height).textSp
        )

        Button(
            onClick = onRetry
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorRowPreview() {
    RetryRow(message = "Oopsie!", onRetry = {})
}
