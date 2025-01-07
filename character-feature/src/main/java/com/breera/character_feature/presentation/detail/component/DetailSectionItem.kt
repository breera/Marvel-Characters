package com.breera.character_feature.presentation.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.breera.character_feature.domain.model.Item
import com.breera.core.presentation.LoadImage
import ir.kaaveh.sdpcompose.sdp

/**
 * Created by Breera Hanif on 07/01/2025.
 */

@Composable
fun SectionItem(item: Item) {
    Column(
        modifier = Modifier
            .padding(start = 10.sdp)
            .width(100.sdp)
    ) {
        LoadImage(
            imageUrl = "${item.resourceURI}",
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.sdp)
                .clip(RoundedCornerShape(8.sdp))
        )

        Text(
            text = item.name ?: "",
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.sdp)
        )
    }
}