package com.example.pixabay.ui.commonComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pixabay.R
import com.example.pixabay.ui.theme.PixabayTheme

@Composable
fun CoilImage(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale,
) {
    Box(modifier = modifier) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build()
        )

        when (painter.state) {
            is AsyncImagePainter.State.Error,
            AsyncImagePainter.State.Empty -> {
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.baseline_broken_image_24),
                    contentDescription = stringResource(id = R.string.app_name),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            is AsyncImagePainter.State.Loading -> {
                DotsLoading(
                    modifier = Modifier.align(Alignment.Center),
                    dotSize = 12.dp,
                    dotColor = MaterialTheme.colorScheme.primary
                )
            }

            is AsyncImagePainter.State.Success -> {
                // This will be executed during the first composition if the image is in the memory cache.
            }
        }
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = contentScale,
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        CoilImage(
            imageUrl = "https://www.test.com",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.FillHeight,
        )
    }
}