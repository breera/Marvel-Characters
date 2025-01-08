package com.breera.character_feature.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.breera.character_feature.data.remote.charactersubinfo.SingleSectionPagingSource
import com.breera.character_feature.domain.CharactersRepository
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.SingleSectionInfoModel
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

    /**
     * Fetches a paginated stream of Single section data.
     * @param list A list of `Item` objects that will be processed to extract single section information.
     * @return A Flow emitting PagingData of Single Section.
     */
    override fun getSingleSectionInfo(
        list: List<Item>
    ): Flow<PagingData<SingleSectionInfoModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SingleSectionPagingSource(list, defaultRepository)
            }
        ).flow
    }
}