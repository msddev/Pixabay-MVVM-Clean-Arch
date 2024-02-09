package com.example.pixabay.ui.commonComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.util.textSp

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    message: String,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_standard)
            ),
            text = message,
            textAlign = TextAlign.Center,
            lineHeight = dimensionResource(id = R.dimen.line_height).textSp
        )

        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size_x_large))
                .padding(top = dimensionResource(id = R.dimen.padding_standard)),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorColumnPreview() {
    EmptyView(message = "Oops!")
}
