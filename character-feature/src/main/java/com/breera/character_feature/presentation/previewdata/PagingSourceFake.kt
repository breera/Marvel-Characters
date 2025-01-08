package com.breera.character_feature.presentation.previewdata

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.breera.character_feature.domain.model.Characters

/**
 * A fake implementation of PagingSource for previewing and testing purposes.
 *
 * This class is designed to simulate a data source for paginated content,
 * allowing you to test and preview UI components without needing a live data connection.
 * It provides a fixed list of character data, making it ideal for scenarios where
 * you want to focus on UI logic without the unpredictability of real network calls.
 *
 * @property data A list of Characters used as the static data source.
 */

class PagingSourceFake(private val data: List<Characters>) : PagingSource<Int, Characters>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? = null
}
