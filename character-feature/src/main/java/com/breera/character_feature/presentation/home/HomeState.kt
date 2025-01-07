package com.breera.character_feature.presentation.home

import com.breera.character_feature.domain.model.CharactersModel

/**
 * Created by Breera Hanif on 06/01/2025.
 */

data class HomeState(
    val searchQuery: String = "",
    val searchResult: CharactersModel = CharactersModel(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)