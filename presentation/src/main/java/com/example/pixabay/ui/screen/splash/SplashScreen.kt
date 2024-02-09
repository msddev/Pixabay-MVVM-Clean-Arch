package com.example.pixabay.ui.screen.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixabay.R
import com.example.pixabay.ui.theme.PixabayTheme
import com.example.pixabay.util.textSp
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen(
    navigateToSearchScreen: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.pixabay_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(dimensionResource(id = R.dimen.padding_small)),
            text = stringResource(R.string.loading),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            fontSize = dimensionResource(id = R.dimen.text_size_medium).textSp,
            color = MaterialTheme.colorScheme.surface,
        )
    }
    LaunchedEffect(Unit) {
        delay(1500)
        navigateToSearchScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
    PixabayTheme {
        SplashScreen(
            navigateToSearchScreen = {

            }
        )
    }
}