package com.breera.character_feature.presentation.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.breera.character_feature.R
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.presentation.home.component.CharacterItem
import com.breera.character_feature.presentation.previewdata.PagingSourceFake
import com.breera.character_feature.presentation.previewdata.character
import com.breera.core.presentation.loader.LoadingSpinner
import com.breera.theme.theme.MarvelCharactersAppTheme
import com.breera.theme.theme.grey
import com.breera.theme.theme.greyLight
import ir.kaaveh.sdpcompose.sdp
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by Breera Hanif on 05/01/2025.
 * The entry point for the home screen, setting up the theme and view model.
 */

@Composable
fun HomeScreenRoot(onClick: (Characters) -> Unit) {
    val context = LocalContext.current
    MarvelCharactersAppTheme {
        val viewModel = koinViewModel<HomeVM>()
        val characters = viewModel.characters.collectAsLazyPagingItems()
        HomeScreen(characters) {
            when (it) {
                HomeAction.OnSearchClick -> {
                    Toast.makeText(context, "Optional to implement", Toast.LENGTH_LONG).show()
                }

                is HomeAction.OnDetailCharacterRequest -> {
                    onClick(it.character)
                }
            }
        }
    }
}

/**
 * Displays the main content of the home screen,
 * including the logo, search icon, and character list.
 */
@Composable
fun HomeScreen(characters: LazyPagingItems<Characters>, onClick: (HomeAction) -> Unit) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
            .statusBarsPadding()
    ) {
        val (logo, searchIcon, characterList) = createRefs()
        Image(
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(70.sdp, 30.sdp),
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
        ) {
            Icon(
                modifier = Modifier.size(40.sdp),
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        MarvelCharacterList(
            modifier = Modifier
                .padding(top = 10.sdp)
                .constrainAs(characterList) {
                    top.linkTo(logo.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                },
            characters = characters
        )
        {
            onClick(HomeAction.OnDetailCharacterRequest(it))
        }
    }
}

/**
 * Marvel character list
 * Renders the list of Marvel characters, handling loading and error states.
 * @param modifier
 * @param characters lazy page items
 * @param onCharacterClick click behaviour
 */

@Composable
fun MarvelCharacterList(
    modifier: Modifier,
    characters: LazyPagingItems<Characters>,
    onCharacterClick: (Characters) -> Unit
) {
    val loadState = characters.loadState

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(50.sdp)
                )
            }

            // Full-Screen Error State (Initial Load)
            is LoadState.Error -> {
                val error = (loadState.refresh as LoadState.Error).error
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    text = error.localizedMessage ?: "An unknown error occurred",
                )
            }

            else -> {
                if (characters.itemCount != 0) {
                    LazyColumn(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(bottom = 10.sdp)
                    ) {
                        items(characters.itemCount) { index ->
                            characters[index]?.let {
                                CharacterItem(
                                    character = it,
                                    onClick = { onCharacterClick(it) }
                                )
                            }
                        }

                        characters.apply {
                            when {
                                loadState.append is LoadState.Loading -> {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(grey)
                                                .height(100.sdp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            LoadingSpinner(color = greyLight)
                                        }
                                    }
                                }

                                loadState.refresh is LoadState.Error -> {
                                    val error = loadState.refresh as LoadState.Error
                                    item { Text("Error: ${error.error.message}") }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Preview home screen
 *
 */
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val dummyList = (0..100).map { character }
    // Create a fake LazyPagingItems using a Pager
    val pager = Pager(PagingConfig(pageSize = 1)) {
        PagingSourceFake(dummyList)
    }.flow.collectAsLazyPagingItems()

    HomeScreen(characters = pager, onClick = {})
}


