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
import androidx.compose.ui.tooling.preview.Preview
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.presentation.previewdata.character
import com.breera.core.presentation.LoadImage
import com.breera.theme.theme.MarvelCharactersAppTheme
import ir.kaaveh.sdpcompose.sdp

/**
 * Created by Breera Hanif on 07/01/2025.
 *
 * This file contains the UI component for displaying a single character item.
 *
 * The `CharacterItem` composable function is responsible for rendering a character's image and name,
 * making it clickable to trigger further actions, like opening a detailed view.
 *
 * */

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

/**
 * The preview function at the bottom provides a sneak peek into how this component looks in isolation,
 * dressed up in the app's theme, ready for its moment in the spotlight.
 */
@Preview
@Composable
fun CharacterItemPreview(){
    MarvelCharactersAppTheme {
        CharacterItem(character = character) { }
    }
}