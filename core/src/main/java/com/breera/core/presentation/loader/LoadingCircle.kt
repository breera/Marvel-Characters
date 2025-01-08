package com.breera.core.presentation.loader

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays a loading spinner animation.
 * This spinner is your trusty sidekick, spinning in anticipation while the app does its heavy lifting.
 *
 * @param modifier A [Modifier] for customizing the spinner's appearance and behavior.
 * @param durationMillis The duration in milliseconds for one complete fade cycle of the spinner's lines.
 * @param size The size of the spinner, defined as a [Dp] value.
 * @param color The color of the spinner, defaulting to the primary color of the current [MaterialTheme].
 * @param isRefreshing A boolean flag to start or stop the rotation animation.
 */
@Composable
fun LoadingSpinner(
    modifier: Modifier = Modifier,
    durationMillis: Int = 1000,
    size: Dp = 30.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    isRefreshing: Boolean = false
) {
    val transition = rememberInfiniteTransition(label = "")

    // Update to handle 12 alphas (one for each line)
    val alpha1 = transition.fractionTransition(
        initialValue = 1f,
        targetValue = 0.1f,
        durationMillis = durationMillis,
        easing = EaseInOut
    )
    val alpha2 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis / 12,
            easing = EaseInOut
        )
    val alpha3 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 2 / 12,
            easing = EaseInOut
        )
    val alpha4 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 3 / 12,
            easing = EaseInOut
        )
    val alpha5 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 4 / 12,
            easing = EaseInOut
        )
    val alpha6 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 5 / 12,
            easing = EaseInOut
        )
    val alpha7 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 6 / 12,
            easing = EaseInOut
        )
    val alpha8 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 7 / 12,
            easing = EaseInOut
        )
    val alpha9 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 8 / 12,
            easing = EaseInOut
        )
    val alpha10 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 9 / 12,
            easing = EaseInOut
        )
    val alpha11 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 10 / 12,
            easing = EaseInOut
        )
    val alpha12 =
        transition.fractionTransition(
            initialValue = 1f,
            targetValue = 0.1f,
            durationMillis = durationMillis,
            offsetMillis = durationMillis * 11 / 12,
            easing = EaseInOut
        )

    val rotateDegree = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = isRefreshing) {
        if (isRefreshing) {
            rotateDegree.animateTo(
                targetValue = 360f * 2,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = durationMillis * 2,
                        easing = LinearEasing
                    )
                )
            )
        }
    }

    Canvas(
        modifier = modifier
            .size(size)
            .rotate(rotateDegree.value)
    ) {
        val rectSize = Size(width = this.size.width / 4, height = this.size.height / 24)
        for (i in 0 until 12) { // Update to 12 lines
            rotate(30f * i) { // Update rotation increment to 30 degrees
                drawRect(
                    color = color.copy(
                        alpha = when (i) {
                            0 -> alpha1.value
                            1 -> alpha2.value
                            2 -> alpha3.value
                            3 -> alpha4.value
                            4 -> alpha5.value
                            5 -> alpha6.value
                            6 -> alpha7.value
                            7 -> alpha8.value
                            8 -> alpha9.value
                            9 -> alpha10.value
                            10 -> alpha11.value
                            11 -> alpha12.value
                            else -> 1.0f
                        }
                    ),
                    topLeft = center + Offset(x = rectSize.width, y = 0f),
                    size = rectSize,
                    style = Stroke(
                        width = rectSize.height * 2,
                        pathEffect = PathEffect.cornerPathEffect(rectSize.height)
                    )
                )
            }
        }
    }
}
