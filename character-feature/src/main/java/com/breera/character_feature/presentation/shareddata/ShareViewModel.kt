package com.breera.character_feature.presentation.shareddata

import androidx.lifecycle.ViewModel
import com.breera.character_feature.domain.model.Characters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for sharing character data across different parts of the application.
 *
 * `ShareViewModel` is designed to hold and manage the state of a selected character,
 * providing a centralized way to access and update this data. It uses a state flow
 * to allow reactive updates and observations, ensuring that any changes to the selected
 * character are immediately reflected wherever this ViewModel is used.
 */

class ShareViewModel : ViewModel() {
    private var _selectedCharacter = MutableStateFlow<Characters?>(null)
    val selectedCharacter = _selectedCharacter.asStateFlow()

    fun onSelectCharacter(character: Characters?) {
        _selectedCharacter.value = character
    }
}
