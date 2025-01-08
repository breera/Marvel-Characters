package com.breera.character_feature.presentation.detail

import com.breera.character_feature.domain.model.Characters

/**
 * Represents the state of the detail screen, encapsulating loading status and character data.
 *
 * `DetailState` is used to manage and convey the current state of the detail view, indicating
 * whether data is still being loaded and holding the character information once it's available.
 *
 * @property isLoading A boolean flag indicating whether the character data is currently being loaded.
 *                     Defaults to true, assuming the data is loading until proven otherwise.
 * @property character The character object containing detailed information, or null if not yet available.
 */

data class DetailState(
    val isLoading: Boolean = true,
    val character: Characters? = null,
)
