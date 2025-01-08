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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.breera.character_feature.R
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.SingleSectionInfoModel
import com.breera.character_feature.domain.model.Url
import com.breera.character_feature.presentation.detail.component.SectionLazyRowComposable
import com.breera.character_feature.presentation.detail.component.SectionsTitle
import com.breera.character_feature.presentation.previewdata.PagingSourceFake
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
    val comicList = viewModel.comicSectionInfo.collectAsLazyPagingItems()
    val storiesList = viewModel.storiesSectionInfo.collectAsLazyPagingItems()
    val seriesList = viewModel.seriesSectionInfo.collectAsLazyPagingItems()
    val eventsList = viewModel.eventsSectionInfo.collectAsLazyPagingItems()
    state.character?.let { character ->
        DetailScreen(
            character = character,
            comicList = comicList,
            storiesList = storiesList,
            seriesList = seriesList,
            eventsList = eventsList
        ) {
            onClick.invoke(it)
        }
    }
}

/**
 * Displays the detailed view of a character, including sections for comics, series, stories, and events.
 *
 * @param character The character object containing all the details to be displayed.
 * @param comicList A `LazyPagingItems` object representing the list of comics associated with the character.
 * @param storiesList A `LazyPagingItems` object representing the list of stories associated with the character.
 * @param seriesList A `LazyPagingItems` object representing the list of series associated with the character.
 * @param eventsList A `LazyPagingItems` object representing the list of events associated with the character.
 * @param onClick A lambda function to handle click actions, defined by the `DetailAction`.
 */

@Composable
fun DetailScreen(
    character: Characters,
    comicList: LazyPagingItems<SingleSectionInfoModel>,
    storiesList: LazyPagingItems<SingleSectionInfoModel>,
    seriesList: LazyPagingItems<SingleSectionInfoModel>,
    eventsList: LazyPagingItems<SingleSectionInfoModel>,
    onClick: (DetailAction) -> Unit
) {
    MarvelCharactersAppTheme {
        Box(Modifier.fillMaxSize()) {
            LoadImage(
                imageUrl = "${character.thumbnail?.path}.${character.thumbnail?.extension}",
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(80.sdp),
                loadingComposable = {
                    Box(Modifier.background(MaterialTheme.colorScheme.background))
                }
            )
            LazyColumn(
                Modifier
                    .fillMaxSize()
            ) {
                item {
                    IntroSection(character) {
                        onClick.invoke(it)
                    }
                }
                if (character.comics?.items.isNullOrEmpty().not()) {
                    item {
                        SectionLazyRowComposable(
                            comicList,
                            character.comics?.items ?: emptyList(),
                            stringResource(R.string.comics)
                        )
                    }
                }
                if (character.series?.items.isNullOrEmpty().not()) {
                    item {
                        SectionLazyRowComposable(
                            seriesList,
                            character.series?.items ?: emptyList(),
                            stringResource(R.string.series)
                        )
                    }
                }
                if (character.stories?.items.isNullOrEmpty().not()) {
                    item {
                        SectionLazyRowComposable(
                            storiesList,
                            character.stories?.items ?: emptyList(),
                            stringResource(R.string.stories)
                        )
                    }
                }
                if (character.events?.items.isNullOrEmpty().not()) {
                    item {
                        SectionLazyRowComposable(
                            eventsList,
                            character.events?.items ?: emptyList(),
                            stringResource(R.string.events)
                        )
                    }
                }

                character.urls?.let {
                    item { SectionsTitle(stringResource(R.string.related_links).uppercase()) }
                    // Related links Section
                    items(it) { detail ->
                        RelatedLinks(detail)
                    }
                }
            }
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
        if (character.description.isNullOrEmpty().not())
            Text(
                text = stringResource(R.string.description),
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
            text = url?.type?.uppercase() ?: "",
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
    val dummyList = (0..100).map {
        SingleSectionInfoModel(
            id = 4924,
            name = "Marsha Berry",
            imageUrl = "http://www.bing.com/search?q=nec"
        )
    }
    // Create a fake LazyPagingItems using a Pager
    val pager = Pager(PagingConfig(pageSize = 1)) {
        PagingSourceFake(dummyList)
    }.flow.collectAsLazyPagingItems()
    DetailScreen(
        character = character,
        comicList = pager, storiesList = pager, seriesList = pager, eventsList = pager
    ) {}
}
