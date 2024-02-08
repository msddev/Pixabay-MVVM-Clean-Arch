package com.example.pixabay.ui.commonComponent

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pixabay.R

@Composable
fun DotsLoading(
    modifier: Modifier = Modifier,
    dotSize: Dp = 24.dp,
    delayUnit: Int = 300, // you can change delay to change animation speed
    dotColor: Color = Color.White,
) {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(id = R.string.app_name))
    val scale1 by animateScaleWithDelay(
        1f,
        target = 0.3f,
        infiniteTransition = infiniteTransition,
        delayUnit = delayUnit,
    )
    val scale2 by animateScaleWithDelay(
        start = 0.3f,
        target = 1f,
        infiniteTransition = infiniteTransition,
        delayUnit = delayUnit,
    )
    val scale3 by animateScaleWithDelay(
        start = 1f,
        target = 0.3f,
        infiniteTransition = infiniteTransition,
        delayUnit = delayUnit,
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        val spaceSize = 2.dp
        Dot(scale = scale1, dotSize = dotSize, dotColor = dotColor)
        Spacer(Modifier.width(spaceSize))
        Dot(scale = scale2, dotSize = dotSize, dotColor = dotColor)
        Spacer(Modifier.width(spaceSize))
        Dot(scale = scale3, dotSize = dotSize, dotColor = dotColor)
    }
}

@Composable
private fun Dot(
    scale: Float,
    dotSize: Dp,
    dotColor: Color,
) = Spacer(
    Modifier
        .size(dotSize)
        .scale(scale)
        .background(
            color = dotColor,
            shape = CircleShape,
        ),
)

@Composable
private fun animateScaleWithDelay(
    start: Float,
    target: Float,
    infiniteTransition: InfiniteTransition,
    delayUnit: Int
) = infiniteTransition.animateFloat(
    initialValue = start,
    targetValue = target,
    animationSpec = infiniteRepeatable(
        animation = keyframes {
            durationMillis = delayUnit * 4
            start at 0 with LinearEasing
            target at 0 with LinearEasing
            start at 0 + delayUnit * 2
        },
    ),
    label = stringResource(id = R.string.app_name),
)

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun DotsPreview() = MaterialTheme {
    Column(modifier = Modifier.padding(4.dp)) {
        DotsLoading()
    }
}