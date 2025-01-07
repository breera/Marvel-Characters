package com.breera.character_feature.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.breera.character_feature.R
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.Url
import com.breera.character_feature.presentation.detail.component.SectionItem
import com.breera.character_feature.presentation.detail.component.SectionsTitle
import com.breera.core.presentation.LoadImage
import com.breera.theme.theme.MarvelCharactersAppTheme
import ir.kaaveh.sdpcompose.sdp

/**
 * Created by Breera Hanif on 06/01/2025.
 */

@Composable
fun DetailScreen(character: Characters) {
    MarvelCharactersAppTheme {

        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                IntroSection(character)
            }
            if (character.comics?.items.isNullOrEmpty().not()) {
                item {
                    Section(
                        title = stringResource(R.string.comics),
                        comics = character.comics?.items ?: emptyList()
                    )
                }
            }
            if (character.series?.items.isNullOrEmpty().not()) {
                item {
                    Section(
                        title = stringResource(R.string.series),
                        comics = character.series?.items ?: emptyList()
                    )
                }
            }
            if (character.stories?.items.isNullOrEmpty().not()) {
                item {
                    Section(
                        title = stringResource(R.string.stories),
                        comics = character.stories?.items ?: emptyList()
                    )
                }
            }
            if (character.events?.items.isNullOrEmpty().not()) {
                item {
                    Section(
                        title = stringResource(R.string.events),
                        comics = character.events?.items ?: emptyList()
                    )
                }
            }

            character.urls?.let {
                // Related links Section
                items(it) { detail ->
                    RelatedLinks(detail)
                }
            }
        }
    }
}

@Composable
fun Section(title: String, comics: List<Item>) {
    SectionsTitle(title)
    LazyRow {
        items(comics) {
            SectionItem(it)
        }
    }
}

@Composable
fun IntroSection(character: Characters) {
    Column {
        // Header Image
        LoadImage(
            imageUrl = "${character.thumbnail?.path}.${character.thumbnail?.extension}",
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(250.sdp)
                .fillMaxWidth()
        )

        // Name Section
        Text(
            text = stringResource(R.string.name),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 10.sdp, top = 16.sdp)
        )
        Text(
            text = character.name ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 10.sdp, top = 8.sdp)
        )

        // Description Section
        Text(
            text = stringResource(R.string.name),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 10.sdp, top = 8.sdp)
        )
        Text(
            text = character.description ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 10.sdp, top = 8.sdp)
        )
    }
}

@Composable
fun RelatedLinks(url: Url?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.sdp, vertical = 12.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = url?.type ?: "",
            style = MaterialTheme.typography.bodyMedium
        )

        // Arrow icon
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = "Arrow",
            tint = Color.Gray
        )
    }
}
