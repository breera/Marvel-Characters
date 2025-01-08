package com.breera.character_feature.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.breera.character_feature.domain.CharactersRepository
import com.breera.character_feature.domain.model.Characters
import kotlinx.coroutines.flow.Flow

/**
 * Created by Breera Hanif on 05/01/2025.
 *
 * CharactersRepositoryImpl is the concrete implementation of the CharactersRepository interface.
 */
class CharactersRepositoryImpl(private val defaultRepository: DefaultRepository) :
    CharactersRepository {
    /**
     * Fetches a paginated stream of character data.
     *
     * @param offset The starting point in the list of characters, though this implementation doesn't use it directly.
     * @param limit The maximum number of characters to fetch at once, but the Pager handles this with its own config.
     * @return A Flow emitting PagingData of Characters, a steady stream of comic book legends.
     */
    override fun getCharacters(offset: Int, limit: Int): Flow<PagingData<Characters>> {
        // The Pager for handling the pagination logic so we can focus on the big picture.
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(defaultRepository)
            }
        ).flow
    }
}