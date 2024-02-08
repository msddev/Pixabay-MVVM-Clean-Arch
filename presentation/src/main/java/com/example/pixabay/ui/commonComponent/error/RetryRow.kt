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
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            imageVector = Icons.Filled.Face,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = message,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )

        Button(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = onRetry
        ) {
            Text(text = "Retry")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorRowPreview() {
    RetryRow(message = "Oopsie!", onRetry = {})
}
