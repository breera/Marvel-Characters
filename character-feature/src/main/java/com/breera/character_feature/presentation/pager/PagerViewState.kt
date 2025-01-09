package com.breera.character_feature.presentation.pager

import com.breera.character_feature.domain.model.Item

/**
 * Represents the state of the pager view.
 *
 * @property item A list of items to be displayed in the pager. Can be null if no items are available.
 */

data class PagerViewState(
    val item: List<Item>? = null,
)
