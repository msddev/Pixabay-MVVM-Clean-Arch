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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pixabay.R
import com.example.pixabay.model.ImagePresentationModel
import com.example.pixabay.ui.commonComponent.CoilImage
import com.example.pixabay.ui.theme.DarkBlue
import com.example.pixabay.ui.theme.LightBlue
import com.example.pixabay.ui.theme.PixabayTheme
import com.example.pixabay.ui.theme.Purple40

@Composable
internal fun ImageDetailContent(
    imageDetail: ImagePresentationModel,
    onBackClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CoilImage(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            imageUrl = imageDetail.largeImageURL,
            contentScale = ContentScale.Crop
        )

        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .size(size = 42.dp),
            onClick = onBackClick,
            backgroundColor = MaterialTheme.colors.background,
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBlue.copy(alpha = 0.4f))
                .padding(dimensionResource(id = R.dimen.padding_x_small))
                .align(Alignment.BottomCenter),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().background(Color.Blue),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.baseline_favorite_24),
                    tint = Color.White,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = imageDetail.likes.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = LightBlue
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_2x_small)))

                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.baseline_comment_24),
                    tint = Color.White,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = imageDetail.comments.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = LightBlue
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_2x_small)))

                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.baseline_cloud_download_24),
                    tint = Color.White,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = imageDetail.downloads.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = LightBlue
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = imageDetail.user,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = LightBlue
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_2x_small)))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = imageDetail.tags,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Purple40
            )
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        ImageDetailContent(
            imageDetail = ImagePresentationModel(),
            onBackClick = {}
        )
    }
}