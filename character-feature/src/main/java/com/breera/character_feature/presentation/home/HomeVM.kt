package com.breera.character_feature.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.breera.character_feature.domain.GetCharactersUseCase
import com.breera.character_feature.domain.model.Characters
import kotlinx.coroutines.flow.Flow

/**
 * Created by Breera Hanif on 06/01/2025.
 */

class HomeVM(charactersUseCase: GetCharactersUseCase) : ViewModel() {
    val characters: Flow<PagingData<Characters>> =
        charactersUseCase().cachedIn(viewModelScope)
}