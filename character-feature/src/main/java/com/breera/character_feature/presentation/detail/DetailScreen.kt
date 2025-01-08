package com.breera.character_feature.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.breera.character_feature.R
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.Url
import com.breera.character_feature.presentation.detail.component.SectionItem
import com.breera.character_feature.presentation.detail.component.SectionsTitle
import com.breera.character_feature.presentation.previewdata.character
import com.breera.core.presentation.LoadImage
import com.breera.theme.theme.MarvelCharactersAppTheme
import ir.kaaveh.sdpcompose.sdp

/**
 * Created by Breera Hanif on 06/01/2025.
 * The root composable for the detail screen, responsible for managing state and invoking actions.
 *
 * @param viewModel The ViewModel providing the character detail state.
 * @param onClick A lambda function to handle click actions, defined by the DetailAction.
 */

@Composable
fun DetailScreenRoot(viewModel: DetailVM, onClick: (DetailAction) -> Unit) {
    val state by viewModel.characterDetailState.collectAsStateWithLifecycle()
    state.character?.let { character ->
        DetailScreen(character) {
            onClick.invoke(it)
        }
    }
}

/**
 * Displays the detailed view of a character, including sections for comics, series, stories, and events.
 *
 * @param character The character object containing all the details to be displayed.
 * @param onClick A lambda function to handle click actions, defined by the DetailAction.
 */

@Composable
fun DetailScreen(character: Characters, onClick: (DetailAction) -> Unit) {
    MarvelCharactersAppTheme {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                IntroSection(character) {
                    onClick.invoke(it)
                }
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

/**
 * Displays a section of items, such as comics, series, stories, or events.
 *
 * @param title The title of the section.
 * @param comics A list of items to be displayed in the section.
 */

@Composable
fun Section(title: String, comics: List<Item>) {
    SectionsTitle(title)
    LazyRow {
        items(comics) {
            SectionItem(it)
        }
    }
}

/**
 * Displays the introductory section of the character detail, including the header image and basic information.
 *
 * @param character The character object containing the introductory details.
 * @param onClick A lambda function to handle click actions, such as navigating back.
 */

@Composable
fun IntroSection(character: Characters, onClick: (DetailAction) -> Unit) {
    Column {
        // Header Image
        Box {
            LoadImage(
                imageUrl = "${character.thumbnail?.path}.${character.thumbnail?.extension}",
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.sdp)
                    .fillMaxWidth()
            )
            IconButton(
                onClick = {
                    onClick.invoke(DetailAction.OnBackClick)
                },
                modifier = Modifier.statusBarsPadding()
            ) {
                Icon(
                    modifier = Modifier.size(40.sdp),
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

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
        character.description?.let { description ->
            Text(
                text = stringResource(R.string.description),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 10.sdp, top = 8.sdp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 10.sdp, top = 8.sdp)
            )
        }
    }
}

/**
 * Displays a row of related links for the character, such as external resources or references.
 *
 * @param url The URL object containing the type and link information.
 */

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

/**
 * Provides a preview of the DetailScreen composable for design-time inspection.
 */

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(character = character) {}
}
