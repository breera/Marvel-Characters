package com.breera.character_feature.domain

import androidx.paging.PagingData
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.SingleSectionInfoModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Breera Hanif on 07/01/2025.
 *  Domain layer's use case , entry point of communication with presentation
 */
class GetSectionInfoUseCase(private val repository: CharactersRepository) {

    operator fun invoke(list: List<Item>): Flow<PagingData<SingleSectionInfoModel>> {
        return repository.getSingleSectionInfo(list)
    }
}
