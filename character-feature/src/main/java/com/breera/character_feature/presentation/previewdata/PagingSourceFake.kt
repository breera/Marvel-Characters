package com.breera.character_feature.presentation.previewdata

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * A fake implementation of `PagingSource` for testing purposes, designed to work with any type of data.
 *
 * This class provides a simple way to simulate paging behavior by returning a predefined list of data
 * as a single page. It can be used in unit tests to verify paging logic without the need for a real
 * data source.
 *
 * @param T The type of data being paged.
 * @property data A list of data items of type `T` to be returned as a single page.
 */

class PagingSourceFake<T : Any>(private val data: List<T>) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null
}
