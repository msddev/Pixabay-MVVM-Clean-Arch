package com.example.pixabay.ui.screen.search.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.ui.commonComponent.CoilImage
import com.example.pixabay.ui.theme.PixabayTheme
import com.example.pixabay.util.textSp

@OptIn(ExperimentalFoundationApi::class)
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
            .height(dimensionResource(id = R.dimen.image_item_height))
            .padding(dimensionResource(id = R.dimen.padding_x_small)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)),
        elevation = CardDefaults.cardElevation(
            dimensionResource(id = R.dimen.card_elevation),
        ),
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
                    .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f))
                    .padding(dimensionResource(id = R.dimen.padding_x_small))
                    .align(Alignment.BottomCenter),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = username,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.text_size_default).textSp,
                    color = MaterialTheme.colorScheme.surface
                )
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
                    fontSize = dimensionResource(id = R.dimen.text_size_medium).textSp,
                    color = MaterialTheme.colorScheme.surface
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
        Surface {
            SearchResultItemView(
                thumbnail = "",
                username = "Masoud",
                tags = "Test, Test",
                onItemClick = {

                }
            )
        }
    }
}