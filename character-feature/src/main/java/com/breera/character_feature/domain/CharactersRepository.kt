package com.breera.character_feature.domain

import androidx.paging.PagingData
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.SingleSectionInfoModel
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

    /**
     * Retrieves a flow of paginated data containing information about a single section.
     *
     * This method takes a list of items and transforms it into a flow of `PagingData` objects,
     * each representing a model of single section information. The flow is designed to handle
     * large datasets efficiently by loading data in pages, reducing memory consumption and
     * improving performance.
     *
     * @param list A list of `Item` objects that will be processed to extract single section information.
     * @return A `Flow` emitting `PagingData` of `SingleSectionInfoModel`, allowing for reactive
     *         data handling and UI updates.
     */
    fun getSingleSectionInfo(list: List<Item>): Flow<PagingData<SingleSectionInfoModel>>
}
