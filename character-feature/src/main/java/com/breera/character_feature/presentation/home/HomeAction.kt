package com.breera.character_feature.presentation.home

import com.breera.character_feature.domain.model.Characters

/**
 * Created by Breera Hanif on 06/01/2025.
 */

sealed interface HomeAction {

    data object OnSearchClick : HomeAction

    /**
     * Action representing a request for detailed information.
     *
     * @property character The Characters containing detailed data.
     */
    data class OnDetailCharacterRequest(val character: Characters) : HomeAction
}