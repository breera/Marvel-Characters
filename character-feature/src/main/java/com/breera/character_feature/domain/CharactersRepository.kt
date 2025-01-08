package com.breera.character_feature.domain

import androidx.paging.PagingData
import com.breera.character_feature.domain.model.Characters
import kotlinx.coroutines.flow.Flow

/**
 * Created by Breera Hanif on 05/01/2025.
 * The CharactersRepository interface is the contract for fetching character data.
 * It's the blueprint for any class that wants to play the role of a data dealer in this operation.
 * */

interface CharactersRepository {
    /**
     * Fetches a stream of character data, paginated for your scrolling pleasure.
     *
     * @param offset The starting point in the list of characters.
     * @param limit The maximum number of characters to fetch at once.
     * @return A Flow emitting PagingData of Characters.
     */
    fun getCharacters(offset: Int, limit: Int): Flow<PagingData<Characters>>
}
