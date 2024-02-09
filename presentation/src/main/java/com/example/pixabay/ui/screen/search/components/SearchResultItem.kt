package com.example.pixabay.ui.screen.search.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pixabay.R
import com.example.pixabay.ui.commonComponent.CoilImage
import com.example.pixabay.ui.theme.DarkBlue
import com.example.pixabay.ui.theme.Gray55
import com.example.pixabay.ui.theme.GreenLight
import com.example.pixabay.ui.theme.LightBlue
import com.example.pixabay.ui.theme.PixabayTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
internal fun SearchResultItem(
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
        backgroundColor = Gray55,
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
                    .background(DarkBlue.copy(alpha = 0.4f))
                    .padding(dimensionResource(id = R.dimen.padding_x_small))
                    .align(Alignment.BottomCenter),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = username,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = LightBlue
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

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        SearchResultItem(
            thumbnail = "",
            username = "Masoud",
            tags = "Test, Test",
            onItemClick = {

            }
        )
    }
}