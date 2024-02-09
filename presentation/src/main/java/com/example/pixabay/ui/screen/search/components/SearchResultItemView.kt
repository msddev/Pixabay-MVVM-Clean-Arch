package com.example.pixabay.ui.screen.search.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.GreenLight
import com.example.pixabay.R
import com.example.pixabay.ui.commonComponent.CoilImage
import com.example.pixabay.ui.theme.PixabayTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
internal fun SearchResultItemView(
    thumbnail: String,
    username: String,
    tags: String,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_x_small),
                horizontal = dimensionResource(id = R.dimen.padding_small)
            ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)),
        elevation = 6.dp,
        onClick = onItemClick,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                imageUrl = thumbnail,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.4f))
                    .padding(dimensionResource(id = R.dimen.padding_x_small))
                    .align(Alignment.BottomCenter),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = username,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.surface
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_2x_small)))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(
                            // Animate forever.
                            iterations = Int.MAX_VALUE,
                        ),
                    text = tags,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = GreenLight
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        SearchResultItemView(
            thumbnail = "",
            username = "Masoud",
            tags = "Test, Test",
            onItemClick = {

            }
        )
    }
}