package com.breera.character_feature.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.breera.character_feature.R
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.CharactersModel
import com.breera.character_feature.presentation.home.component.CharacterItem
import com.breera.character_feature.presentation.previewdata.character
import com.breera.theme.theme.MarvelCharactersAppTheme
import ir.kaaveh.sdpcompose.sdp

/**
 * Created by Breera Hanif on 05/01/2025.
 */

@Composable
fun HomeScreenRoot() {
    MarvelCharactersAppTheme {
        HomeScreen {
            when (it) {
                HomeAction.OnSearchClick -> {
                    // do search
                }

                is HomeAction.OnDetailCharacterRequest -> {
                    // move to next screen
                }
            }
        }
    }
}


@Composable
fun HomeScreen(onClick: (HomeAction) -> Unit) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        val (logo, searchIcon, characterList) = createRefs()
        Image(
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(80.sdp, 30.sdp),
            painter = painterResource(R.drawable.marvel_logo),
            contentDescription = ""
        )
        IconButton(
            onClick = {
                onClick.invoke(HomeAction.OnSearchClick)
            },
            modifier = Modifier
                .constrainAs(searchIcon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .size(40.sdp),
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        MarvelCharacterList(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.sdp)
                .constrainAs(characterList) {
                    top.linkTo(logo.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                },
            CharactersModel(
                // will be replaced with Actual data
                results = (1..10).map { character })
        )
        {}
    }
}


@Composable
fun MarvelCharacterList(
    modifier: Modifier,
    characters: CharactersModel,
    onCharacterClick: (Characters) -> Unit
) {
    if (characters.results.isNullOrEmpty().not()) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(characters.results ?: emptyList()) { character ->
                CharacterItem(
                    character = character,
                    onClick = { onCharacterClick(character) }
                )
            }
        }
    }
}
