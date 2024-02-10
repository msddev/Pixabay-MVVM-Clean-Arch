package com.example.pixabay.ui.commonComponent.error

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.ui.theme.PixabayTheme
import com.example.pixabay.util.textSp

@Composable
fun RetryColumn(
    modifier: Modifier = Modifier,
    message: String,
    onRetryClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_standard)
            ),
            text = message,
            textAlign = TextAlign.Center,
            lineHeight = dimensionResource(id = R.dimen.line_height).textSp,
        )

        Icon(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_standard))
                .size(dimensionResource(id = R.dimen.icon_size_large)),
            imageVector = Icons.Filled.Info,
            contentDescription = null,
        )

        Button(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_standard)),
            onClick = onRetryClick
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorColumnPreview() {
    PixabayTheme {
        Surface {
            RetryColumn(message = "Oops!", onRetryClick = {})
        }
    }
}
