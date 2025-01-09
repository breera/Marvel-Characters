package com.breera.character_feature.presentation.detail

import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.Item

/**
 * Created by Breera Hanif on 06/01/2025.
 *
 * A sealed interface representing the possible actions in the detail screen.
 *
 * `DetailAction` serves as a blueprint for defining user interactions within the
 * character detail view. By using a sealed interface, we ensure that all possible
 * actions are explicitly defined, making it easier to handle them in a type-safe manner.
 */

sealed interface DetailAction {
    /**
     * Represents the action of clicking the back button.
     *
     * This action is triggered when the user wants to navigate away from the detail screen,
     * typically returning to the previous screen in the navigation stack.
     */
    data object OnBackClick : DetailAction

    /**
     * Represents the action of clicking a category within the detail screen.
     *
     * This action is used when the user interacts with a category, potentially to
     * view more information related to that category.
     */
    data class OnCategoryClick(val list: List<Item>, val type: SectionType) : DetailAction

    /**
     * Represents the action of changing the selected character.
     *
     * This action is triggered when a new character is selected, allowing the detail view
     * to update and display information about the newly selected character.
     *
     * @property characters The new character that has been selected.
     */
    data class OnSelectedCharacterChange(val characters: Characters) : DetailAction
}
