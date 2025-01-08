package com.breera.character_feature.presentation.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Created by Breera Hanif on 06/01/2025.
 * ViewModel for managing the state and actions of the detail screen.
 *
 * `DetailVM` is responsible for holding and updating the state of the detail view,
 * reacting to user actions, and ensuring the UI is in sync with the underlying data.
 * It uses a state flow to manage the `DetailState`, providing a reactive way to update
 * and observe the state changes.
*/

class DetailVM : ViewModel() {
    private val _characterDetailState = MutableStateFlow(DetailState())
    val characterDetailState = _characterDetailState.asStateFlow()

    /**
     * Handles actions dispatched from the UI, updating the state accordingly.
     *
     * @param action The action to be handled, defined by the `DetailAction` sealed interface.
     */
    fun onAction(action: DetailAction) {
        when (action) {

            is DetailAction.OnSelectedCharacterChange -> {
                _characterDetailState.update {
                    it.copy(character = action.characters)
                }
            }

            else -> {
                // nothing to do for now
            }
        }
    }
}