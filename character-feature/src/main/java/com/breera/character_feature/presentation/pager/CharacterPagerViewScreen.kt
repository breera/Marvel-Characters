package com.breera.character_feature.presentation.pager

/**
 * Created by Breera Hanif on 06/01/2025.
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.breera.character_feature.domain.model.SingleSectionInfoModel
import com.breera.character_feature.presentation.previewdata.PagingSourceFake
import com.breera.core.presentation.LoadImage
import com.breera.core.presentation.loader.LoadingSpinner
import com.breera.theme.theme.MarvelCharactersAppTheme
import com.breera.theme.theme.grey
import com.breera.theme.theme.transparentWhite
import ir.kaaveh.sdpcompose.sdp

/**
 * Root composable for the character pager view screen.
 * Displays a loading indicator and initializes the pager view screen.
 *
 * @param viewModel The ViewModel providing data for the pager.
 * @param onClick Callback for handling click events.
 */

@Composable
fun CharacterPagerViewScreenRoot(viewModel: PagerViewVM, onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(grey)
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
        val state = viewModel.sectionInfo.collectAsLazyPagingItems()
        if (state.itemCount != 0) {
            CharacterPagerViewScreen(state) {
                onClick()
            }
        }
    }
}

/**
 * Displays a horizontal pager for a list of characters with lazy loading.
 * Handles loading and error states, and provides a close button.
 *
 * @param singleCharacterList The list of characters to display.
 * @param onClick Callback for handling click events, such as closing the screen.
 */

@Composable
fun CharacterPagerViewScreen(
    singleCharacterList: LazyPagingItems<SingleSectionInfoModel>,
    onClick: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0) { singleCharacterList.itemCount }
    val loadState = singleCharacterList.loadState
    when (loadState.refresh) {

        is LoadState.Loading -> {
            Column(Modifier.background(grey)) {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            }
        }

        is LoadState.Error -> {
            val error = (loadState.refresh as LoadState.Error).error
            Text(
                modifier = Modifier,
                color = Color.White,
                text = error.localizedMessage ?: "An unknown error occurred",
            )
        }

        else ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                val (closeIcon, pager, imageInfoColumn) = createRefs()

                // Close Icon
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .constrainAs(closeIcon) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                        .clickable { onClick() }
                        .statusBarsPadding()
                        .padding(end = 20.sdp)
                        .size(24.sdp),
                    tint = Color.White
                )

                // Horizontal Pager
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .constrainAs(pager) {
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                            top.linkTo(closeIcon.bottom)
                            bottom.linkTo(imageInfoColumn.top)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 30.sdp, bottom = 20.sdp),
                    pageSpacing = 10.sdp,
                    contentPadding = PaddingValues(horizontal = 20.dp), // Add padding for peek effect
                    beyondViewportPageCount = 1
                ) { page ->
                    // Handle lazy paging item at the given page index
                    val item = singleCharacterList[page]

                    if (item != null) {
                        Column {
                            // Load Image (or placeholder if needed)
                            LoadImage(
                                imageUrl = item.imageUrl ?: "",
                                contentDescription = item.name ?: "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    } else {
                        // Show a loading placeholder while the item is loading
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingSpinner(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                // Title and Page Indicator
                Column(
                    modifier = Modifier
                        .constrainAs(imageInfoColumn) {
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(bottom = 16.sdp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Comic Title
                    val currentPage = pagerState.currentPage
                    val currentItem = singleCharacterList[currentPage]

                    Text(
                        text = currentItem?.name ?: "",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(15.sdp))

                    // Page Indicator
                    val pageIndicatorText = "${currentPage + 1}/${singleCharacterList.itemCount}"
                    Text(
                        text = pageIndicatorText,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = transparentWhite
                        ),
                        modifier = Modifier.padding(bottom = 10.sdp)
                    )
                }
            }
    }
}

/**
 * Preview function for the character pager view screen.
 * Displays a sample list of characters for design and testing purposes.
 */

@Preview
@Composable
fun ComicScreenPreview() {
    MarvelCharactersAppTheme {
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
        CharacterPagerViewScreen(
            pager
        ) {}
    }
}


