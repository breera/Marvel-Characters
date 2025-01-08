package com.breera.character_feature.data.remote.charactersubinfo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.breera.character_feature.data.mappers.toSingleItemModel
import com.breera.character_feature.data.remote.DefaultRepository
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.SingleSectionInfoModel
import com.breera.core.domain.Result

/**
 * Created by Breera Hanif on 08/01/2025.
 * A `PagingSource` implementation for loading paginated data of character sub-sections.
 *
 * This class is responsible for fetching and managing paginated data for a specific section
 * of character items. It uses a predefined list of items and retrieves data in chunks, or pages,
 * using the provided `DefaultRepository`. The paging source handles success and error states,
 * ensuring that data is loaded efficiently and errors are propagated correctly.
 *
 * @property section The list of `Item` objects representing the section to be paginated.
 * @property repository The repository used to fetch section information.
 * @property itemsPerPage The number of items to load per page, defaulting to 5.
 *
*/

class SingleSectionPagingSource(
    private val section: List<Item>, // The preloaded section list
    private val repository: DefaultRepository,
    private val itemsPerPage: Int = 5 // Number of items per page
) : PagingSource<Int, SingleSectionInfoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleSectionInfoModel> {
        val currentPage = params.key ?: 0
        val start = currentPage * itemsPerPage
        val end = minOf(start + itemsPerPage, section.size)
        return try {
            val successfulItems = mutableListOf<SingleSectionInfoModel>()
            repository.getSingleSectionInfo(section, start, end).map { response ->
                when (response) {
                    is Result.Error -> {
                        val e = Exception(response.error.toString())
                        LoadResult.Error<Int, SingleSectionInfoModel>(e)
                    }

                    is Result.Success -> {
                        successfulItems.add(response.data.toSingleItemModel())
                    }
                }
            }

            LoadResult.Page(
                data = successfulItems,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (end < section.size) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SingleSectionInfoModel>): Int? {
        // Return the key to refresh the PagingSource
        return state.anchorPosition?.let { position ->
            val closestPage = state.closestPageToPosition(position)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }
}
