package com.breera.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.CachePolicy
import coil3.request.ImageRequest

@Composable
fun LoadImage(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Int = 0,
    contentScale: ContentScale = ContentScale.Crop,
    size: Dp = 60.dp,
    loadersize: Dp = 30.dp
) {
    // State for image loading result
    var imageLoadResult by remember { mutableStateOf<Result<Painter>?>(null) }
    // Load the icon,
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED) // Cache on disk
            .memoryCachePolicy(CachePolicy.ENABLED) // Cache in memory
            .build(),
        onSuccess = {
            imageLoadResult =
                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                    Result.success(it.painter)
                } else {
                    Result.failure(Exception("Invalid image size"))
                }
        },
        onError = {
            it.result.throwable.printStackTrace()
            imageLoadResult = Result.failure(it.result.throwable)
        }
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Display the image or a loading spinner,
        when (val result = imageLoadResult) {
            null -> {
                Box(
                    Modifier
                        .size(loadersize),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            }

            else -> {
                Box(modifier.size(size)) {
                    Image(
                        painter = if (result.isSuccess) painter else painterResource(placeholder),
                        contentDescription = contentDescription,
                        contentScale = if (result.isSuccess) contentScale else ContentScale.Fit,
                        modifier = modifier.size(size)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,  // Less gray at the top
                                        Color(0x65000000)  // More gray at the bottom
                                    )
                                )
                            )
                    )
                }
            }
        }
    }
}
