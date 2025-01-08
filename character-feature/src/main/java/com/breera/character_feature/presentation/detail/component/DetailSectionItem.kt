package com.breera.character_feature.presentation.detail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breera.core.presentation.LoadImage
import com.breera.theme.theme.MarvelCharactersAppTheme
import ir.kaaveh.sdpcompose.sdp

/**
 * Created by Breera Hanif on 07/01/2025.
 */

@Composable
fun SectionItem(name: String, imageUrl: String) {
    Column(
        modifier = Modifier
            .padding(start = 10.sdp)
            .width(100.sdp)
    ) {
        LoadImage(
            imageUrl = imageUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.sdp)
                .clip(RoundedCornerShape(8.sdp))
        )

        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.sdp)
        )
    }
}

@Composable
fun SectionItemLoading(name: String) {
    Column(
        modifier = Modifier
            .padding(start = 10.sdp)
            .width(100.sdp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.sdp)
                .clip(RoundedCornerShape(8.sdp))
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(8.sdp))
            )
        }


        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.sdp)
        )
    }
}

@Preview
@Composable
fun SectionItemPreview() {
    MarvelCharactersAppTheme {
        SectionItem(
            name = "Margaret Gill",
            imageUrl = "https://www.google.com/#q=faucibus"
        )
    }
}

@Preview
@Composable
fun SectionItemLoadingPreview() {
    MarvelCharactersAppTheme {
        SectionItemLoading(
            name = "Margaret Gill"
        )
    }
}