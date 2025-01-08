package com.breera.character_feature.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.breera.character_feature.data.mappers.toCharacterModel
import com.breera.character_feature.domain.model.Characters
import com.breera.core.domain.Result

/**
 * Created by Breera Hanif on 07/01/2025.
 *  PagingSource implementation for fetching Marvel
 *  characters from the remote API.
 */

class CharacterPagingSource(
    private val repository: DefaultRepository

) : PagingSource<Int, Characters>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize.coerceAtMost(20) // Align with the API's `limit`
            // Make the API call
            when (val result = repository.getCharacters(offset, limit)) {
                is Result.Success -> {
                    val characterList = result.data.data?.results?.toCharacterModel()
                        ?: emptyList() // Extract the character list
                    // Calculate nextKey based on API response
                    val totalCount = result.data.data?.total ?: 0// Total number of characters
                    val nextKey = if (offset + characterList.size >= totalCount) {
                        null // No more pages
                    } else {
                        offset + limit // Move to the next offset
                    }
                    Log.d("TAG", "load: key ka raphara $nextKey")
                    LoadResult.Page(
                        data = characterList,
                        prevKey = if (offset == 0) null else offset - limit, // Previous offset
                        nextKey = nextKey
                    )
                }

                is Result.Error -> {
                    LoadResult.Error(Exception(result.error.toString()))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        // Use the closest page to reset the state
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}
