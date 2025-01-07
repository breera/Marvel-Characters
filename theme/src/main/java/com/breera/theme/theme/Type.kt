package com.breera.theme.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import ir.kaaveh.sdpcompose.ssp

// Set of Material typography styles to start with
val MarvelCharacters_Typography: Typography
    @Composable
    get() = Typography(
        bodyMedium = TextStyle(
            fontSize = 12.ssp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimary // white
        ),
        titleSmall = TextStyle(
            fontSize = 10.ssp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.primary // white
        ),
        labelMedium = TextStyle(
            fontSize = 10.ssp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    )
