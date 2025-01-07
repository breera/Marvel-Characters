package com.breera.character_feature.presentation.pager

/**
 * Created by Breera Hanif on 06/01/2025.
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.presentation.previewdata.character
import com.breera.core.presentation.LoadImage
import com.breera.theme.theme.transparentWhite
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ComicScreen(item: List<Item>) {
    val pagerState = rememberPagerState(0) { item.size }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (closeIcon, pager, imageInfoColumn) = createRefs()
        // Close icon at the top
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            modifier = Modifier
                .constrainAs(closeIcon) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .padding(top = 20.sdp, end = 15.sdp)
                .size(24.sdp),
            tint = Color.White
        )

        // Comic Pager with images
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
            beyondViewportPageCount = 1 // Keep one extra page visible
        ) { page ->
            Column {
                LoadImage(
                    imageUrl = item[page].resourceURI,
                    contentDescription = item[page].name ?: "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

            }
        }

        // Title and page indicator
        Column(
            modifier =
            Modifier
                .constrainAs(imageInfoColumn) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .padding(bottom = 16.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Comic title
            Text(
                text = item[pagerState.currentPage].name ?: "",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )

            Spacer(modifier = Modifier.height(15.sdp))

            // Page indicator
            val currentPage = pagerState.currentPage + 1
            Text(
                text = "$currentPage/${item.size}",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = transparentWhite
                ),
                modifier = Modifier.padding(bottom = 10.sdp)
            )
        }
    }
}

@Preview
@Composable
fun ComicScreenPreview() {
    ComicScreen(character.comics?.items ?: emptyList())
}


