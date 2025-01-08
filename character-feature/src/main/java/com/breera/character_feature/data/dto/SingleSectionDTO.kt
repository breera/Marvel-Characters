package com.breera.character_feature.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Breera Hanif on 08/01/2025.
 * Single section DTO for data layer
 *
 */
@Serializable
data class SingleSectionDTO(
    @SerialName("data")
    var data: Data? = Data(),
) {
    @Serializable
    data class Data(
        @SerialName("results")
        var results: List<Result>? = listOf(),
    ) {
        @Serializable
        data class Result(
            @SerialName("id")
            var id: Int? = 0,
            @SerialName("title")
            var title: String? = "",
            @SerialName("thumbnail")
            var thumbnail: Thumbnail? = Thumbnail(),

            ) {
            @Serializable
            data class Thumbnail(
                @SerialName("extension")
                var extension: String? = "",
                @SerialName("path")
                var path: String? = ""
            )
        }
    }
}
