package com.breera.character_feature.presentation.detail.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.kaaveh.sdpcompose.sdp

/**
 * Displays a section title within the detail screen.
 *
 * `SectionsTitle` is a simple composable function that renders a title for different sections
 * of the detail view, such as comics, series, stories, and events. It uses the app's typography
 * and applies padding to ensure consistent styling and spacing.
 *
 * @param title The string to be displayed as the section title.
 */
@Composable
fun SectionsTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(start = 10.sdp, top = 25.sdp, bottom = 10.sdp)
    )
}