package com.example.pixabay.ui.screen.detail.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.model.ImagePresentationModel
import com.example.pixabay.ui.commonComponent.CoilImage
import com.example.pixabay.ui.theme.GreenLight
import com.example.pixabay.ui.theme.PixabayTheme
import com.example.pixabay.util.textSp

@Composable
internal fun ImageDetailBody(
    imageDetail: ImagePresentationModel,
    onBackClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CoilImage(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.Center),
            imageUrl = imageDetail.largeImageURL,
            contentScale = ContentScale.Crop
        )

        FloatingActionButton(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_standard))
                .size(dimensionResource(id = R.dimen.icon_size_x_large))
                .align(Alignment.TopStart),
            onClick = onBackClick,
            backgroundColor = MaterialTheme.colorScheme.background,
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(topStartPercent = 15, topEndPercent = 15)
                )
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                .align(Alignment.BottomCenter),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_small)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_standard)),
                    painter = painterResource(id = R.drawable.baseline_favorite_24),
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = dimensionResource(id = R.dimen.padding_x_small)),
                    text = imageDetail.likes.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontSize = dimensionResource(id = R.dimen.text_size_medium).textSp,
                    color = MaterialTheme.colorScheme.surface
                )

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_standard)))

                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_standard)),
                    painter = painterResource(id = R.drawable.baseline_comment_24),
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = dimensionResource(id = R.dimen.padding_x_small)),
                    text = imageDetail.comments.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontSize = dimensionResource(id = R.dimen.text_size_medium).textSp,
                    color = MaterialTheme.colorScheme.surface
                )

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_standard)))

                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_standard)),
                    painter = painterResource(id = R.drawable.baseline_cloud_download_24),
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = dimensionResource(id = R.dimen.padding_x_small)),
                    text = imageDetail.downloads.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontSize = dimensionResource(id = R.dimen.text_size_medium).textSp,
                    color = MaterialTheme.colorScheme.surface
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = imageDetail.user,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.text_size_x_large).textSp,
                color = MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_x_small)))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = imageDetail.tags,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                fontSize = dimensionResource(id = R.dimen.text_size_default).textSp,
                color = GreenLight
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        ImageDetailBody(
            imageDetail = ImagePresentationModel(
                user = "Masoud",
                tags = "Test, Test",
                likes = 120,
                downloads = 140,
                comments = 160
            ),
            onBackClick = {}
        )
    }
}