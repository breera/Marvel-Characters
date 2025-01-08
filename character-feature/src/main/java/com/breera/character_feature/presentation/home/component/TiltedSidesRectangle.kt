package com.breera.character_feature.presentation.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.breera.theme.theme.MarvelCharactersAppTheme
import ir.kaaveh.sdpcompose.sdp

/**
 * Created by Breera Hanif on 07/01/2025.
 * Titled component for name display
 */
@Composable
fun TiltedSidesRectangle(modifier: Modifier, text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(16.sdp)
            .width(120.sdp) // Adjust width
            .height(30.sdp) // Adjust height
            .background(Color.White.copy(alpha = 0.0F)) // Optional for preview background
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
        ) {
            val path = Path().apply {
                moveTo(0f, size.height) // Start from bottom-left
                lineTo(size.width * 0.05f, 0f) // Tilted top-left
                lineTo(size.width * 1.05f, 0f) // Tilted top-right
                lineTo(size.width, size.height) // Bottom-right
                close() // Close the path
            }
            drawPath(
                path = path,
                color = Color.White
            )
        }

        Text(
            text = text,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(horizontal = 12.sdp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun TiltedSidesRectanglePreview() {
    MarvelCharactersAppTheme {
        TiltedSidesRectangle(modifier = Modifier, text = "Sample Text")
    }
}

@Preview
@Composable
fun TiltedSidesRectangleLongTextPreview() {
    MarvelCharactersAppTheme {
        TiltedSidesRectangle(modifier = Modifier, text = "Long Long Long Long Text")
    }
}
