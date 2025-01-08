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
import com.breera.core.R


/**
 * A composable function for loading and displaying an image from a URL with a placeholder and loading indicator.
 *
 * `LoadImage` handles the asynchronous loading of an image using Coil, displaying a placeholder while loading
 * and a loading spinner if needed. It also applies a gradient overlay for a stylish effect.
 *
 * @param imageUrl The URL of the image to load. If null, the placeholder will be shown.
 * @param contentDescription A description of the image for accessibility purposes.
 * @param modifier A Modifier for customizing the layout or appearance of the image.
 * @param placeholder A drawable resource ID to use as a placeholder if the image fails to load.
 * @param contentScale How the image should be scaled within its bounds.
 * @param size The size of the image to be displayed.
 * @param loadersize The size of the loading spinner shown while the image is loading.
 */

@Composable
fun LoadImage(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Int = R.drawable.image_error,
    contentScale: ContentScale = ContentScale.Crop,
    size: Dp = 60.dp,
    loadersize: Dp = 30.dp,
    loadingComposable: @Composable () -> Unit = {
        CircularProgressIndicator(
            color = Color.White
        )
    }
) {
    // State for image loading result
    var imageLoadResult by remember { mutableStateOf<Result<Painter>?>(null) }
    // Load the icon,
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
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
                    loadingComposable()
                }
            }

            else -> {
                Box(
                    modifier
                        .size(size)
                        .background(Color.White)
                ) {
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
