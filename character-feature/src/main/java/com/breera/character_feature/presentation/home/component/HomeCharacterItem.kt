package com.breera.character_feature.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.breera.character_feature.domain.model.Characters
import com.breera.core.presentation.LoadImage
import ir.kaaveh.sdpcompose.sdp

/**
 * Created by Breera Hanif on 07/01/2025.
 */

@Composable
fun CharacterItem(
    character: Characters,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.sdp)
            .clickable(onClick = onClick)
    ) {
        LoadImage(
            imageUrl = "${character.thumbnail?.path}.${character.thumbnail?.extension}",
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        TiltedSidesRectangle(Modifier.align(Alignment.BottomStart), character.name ?: "")
    }
}