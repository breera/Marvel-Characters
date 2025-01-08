package com.breera.character_feature.domain

import androidx.paging.PagingData
import com.breera.character_feature.domain.model.Characters
import kotlinx.coroutines.flow.Flow

/**
 * Created by Breera Hanif on 07/01/2025.
 *  Domain layer's use case , entry point of communication with presentation
 */
class GetCharactersUseCase(private val repository: CharactersRepository) {

    operator fun invoke(): Flow<PagingData<Characters>> {
        return repository.getCharacters(1,1)
    }
}
