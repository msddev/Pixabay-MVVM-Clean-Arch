package com.example.pixabay.ui.commonComponent.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R

@Composable
fun LoadingRow(
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_small),
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_x_large))
        )
        Text(title)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoadingRowPreview() {
    LoadingRow(title = "Please wait...")
}
