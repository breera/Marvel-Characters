package com.breera.character_feature.data.mappers

import com.breera.character_feature.data.dto.SingleSectionDTO
import com.breera.character_feature.domain.model.SingleSectionInfoModel

/**
 * Created by Breera Hanif on 08/01/2025.
 * Mapper to convert Data Model into Domain Model
 */

fun SingleSectionDTO.toSingleItemModel(): SingleSectionInfoModel {
    return SingleSectionInfoModel(
        id = this.data?.results?.get(0)?.id ?: 0,
        name = this.data?.results?.get(0)?.title ?: "",
        imageUrl = "${this.data?.results?.get(0)?.thumbnail?.path}.${this.data?.results?.get(0)?.thumbnail?.extension}",
    )
}
