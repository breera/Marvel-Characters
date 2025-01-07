package com.breera.character_feature.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breera.character_feature.domain.CharactersRepository
import com.breera.character_feature.domain.model.CharactersModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Created by Breera Hanif on 06/01/2025.
 */

class HomeVM(private val charactersRepository: CharactersRepository) : ViewModel() {
    val _state = MutableStateFlow(CharactersModel())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _state.value
    )

    fun getCharactersData() {
        viewModelScope.launch {
            charactersRepository.getCharacters()
        }
    }
}