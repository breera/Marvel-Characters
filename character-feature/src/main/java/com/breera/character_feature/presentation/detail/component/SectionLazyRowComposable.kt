package com.breera.character_feature.presentation.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.SingleSectionInfoModel
import com.breera.core.presentation.loader.LoadingSpinner
import com.breera.theme.theme.grey
import com.breera.theme.theme.greyLight
import ir.kaaveh.sdpcompose.sdp

/**
 * A composable function that displays a lazy row of character section items with a title.
 *
 * This function handles different loading states to provide a seamless user experience.
 * It shows a loading spinner during data fetching, displays error messages when needed,
 * and presents the list of items once they're loaded. The lazy row is designed to
 * efficiently handle large datasets by loading items on demand.
 *
 * @param singleCharacterList A `LazyPagingItems` of `SingleSectionInfoModel` representing the paginated list of section items.
 * @param items A list of `Item` objects used for displaying loading placeholders.
 * @param title The title of the section displayed above the lazy row.
 */

@Composable
fun SectionLazyRowComposable(
    singleCharacterList: LazyPagingItems<SingleSectionInfoModel>,
    items: List<Item>,
    title: String,
    onCLick: () -> Unit
) {
    val loadState = singleCharacterList.loadState
    when (loadState.refresh) {
        is LoadState.Loading -> {
            Column {
                SectionsTitle(title)
                LazyRow(
                    modifier = Modifier
                        .padding(bottom = 10.sdp)
                ) {
                    items(items) {
                        SectionItemLoading(it.name ?: "")
                    }
                }
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

        else -> {
            if (singleCharacterList.itemCount != 0) {
                SectionsTitle(title)
                LazyRow(
                    modifier = Modifier
                        .padding(bottom = 10.sdp)
                ) {

                    items(singleCharacterList.itemCount) { index ->
                        singleCharacterList[index]?.let {
                            SectionItem(it.name, it.imageUrl) {
                                onCLick()
                            }
                        }
                    }

                    singleCharacterList.apply {
                        when {
                            loadState.append is LoadState.Loading -> {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(grey)
                                            .height(140.sdp)
                                            .width(100.sdp)
                                            .padding(start = 20.sdp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        LoadingSpinner(
                                            modifier = Modifier.align(Alignment.Center),
                                            color = greyLight
                                        )
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
