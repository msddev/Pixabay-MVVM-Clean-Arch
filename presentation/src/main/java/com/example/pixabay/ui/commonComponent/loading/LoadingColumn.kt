package com.example.pixabay.ui.commonComponent.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.util.textSp

@Composable
fun LoadingColumn(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_standard)),
            text = title,
            textAlign = TextAlign.Center,
            lineHeight = dimensionResource(id = R.dimen.line_height).textSp
        )
        CircularProgressIndicator(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size_x_large))
                .padding(top = dimensionResource(id = R.dimen.padding_standard))
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoadingColumnPreview() {
    LoadingColumn(title = "Please wait...")
}
