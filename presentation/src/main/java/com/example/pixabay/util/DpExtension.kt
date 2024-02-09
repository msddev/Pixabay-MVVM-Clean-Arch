package com.example.pixabay.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun Dp.SpacerHeight(): Unit = Spacer(modifier = Modifier.height(this))

@Composable
fun Dp.SpacerWidth(): Unit = Spacer(modifier = Modifier.width(this))

@Composable
fun Dp.textSp() = with(LocalDensity.current) {
    this@textSp.toSp()
}

val Dp.textSp: TextUnit
    @Composable get() = with(LocalDensity.current) {
        this@textSp.toSp()
    }

@Composable
fun Dp.sizePx(): Float = with(LocalDensity.current) { this@sizePx.toPx() }

val Dp.sizePx: Float
    @Composable get() = with(LocalDensity.current) {
        this@sizePx.toPx()
    }