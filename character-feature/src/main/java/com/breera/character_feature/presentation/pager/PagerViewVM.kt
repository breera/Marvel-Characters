package com.breera.character_feature.presentation.pager

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.breera.character_feature.domain.GetSectionInfoUseCase
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.SingleSectionInfoModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

/**
 * Created by Breera Hanif on 06/01/2025.
 * ViewModel for managing the state and actions of the detail screen.
 *
 * `PagerViewVM` is responsible for providing particular section,
 */

class PagerViewVM(getSectionInfoUseCase: GetSectionInfoUseCase) : ViewModel() {

    private var _sectionState = MutableStateFlow(PagerViewState())
    val sectionState = _sectionState.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val sectionInfo: Flow<PagingData<SingleSectionInfoModel>> = sectionState
        .filter { it.item?.isNotEmpty() == true } // Ensure the section is non-empty
        .flatMapLatest { section ->
            getSectionInfoUseCase(
                section.item ?: emptyList()
            )
        }


    fun onSectionInfoCollect(item: List<Item>) {
        _sectionState.update {
            it.copy(item = item)
        }
    }
}
